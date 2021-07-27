# Mybatis全局配置文件详解

MyBatis 的全局配置文件，自上而下的编写内容如下：

properties（属性）

settings（设置）

typeAliases（类型别名）

typeHandlers（类型处理器）

objectFactory（对象工厂）

plugins（插件）

environments（环境配置）

- environment（环境变量）

 - - transactionManager（事务管理器）
 - - dataSource（数据源）

databaseIdProvider（数据库厂商标识）

mappers（映射器）


## properties
properties 属性，它的作用是定义全局配置变量，并且它可以加载外部化的 properties 配置文件。

### 加载多个外部化配置文件
如果真的需要一次性加载多个外部化配置文件，使用 xml 配置的方式是无法实现的。
不过我们可以换一种思路来实现：Properties 这个类是可以加载多个 properties 文件到一个对象中的，所以我们可以基于这个思路来实现。


### properties 优先级
配置属性的方式有 3 种：

- 直接在 <properties> 中定义配置属性
- 借助 <properties> 的 resource / url 属性加载外部化配置文件
- 编程式加载外部化配置文件

所以这三种方式定义的配置属性，谁的优先级更高呢？
按照官方文档的说法，MyBatis 会按照上面所述的顺序读取配置属性，而下面的属性会覆盖上面的，所以优先级的顺序就刚好与上面列举的顺序相反：

- 编程式加载的外部化配置文件
- 借助 <properties> 的 resource / url 属性加载的外部化配置文件
- 在 <properties> 中定义的配置属性


## setting

leek-source-read/mybatis-configuration-setting.jpg


## typeAliases 类型别名

typeAliases 类型别名的设置，是考虑到 mapper.xml 中我们每次写实体类的时候，都要写它们的全限定类名，太麻烦。
于是 MyBatis 提供了类型别名的机制。

```xml
<typeAliases>
    <!-- 逐个声明 -->
    <typeAlias alias="Department" type="cn.atong.leek.domain.entity.Department"/>

    <!-- 包扫描
             以此法被扫描的实体类，别名为类名的首字母小写形式(类似于Bean -> bean)
        -->
    <package name="cn.atong.leek.domain.entity"/>
</typeAliases>
```

## typeHandlers 类型处理器

就像在JDBC中，我们在PreparedStatement中设置预编译sql所需的参数或执行sql后根据结果集ResultSet对象获取得到的数据时，需要将数据库中的类型和java中字段的类型进行转换一样，在MyBatis中使用typeHandler来实现。所以说白了，typeHandlers就是用来完成javaType和jdbcType之间的转换。

typeHandlers 类型处理器，它的意义是针对一个特定的 Java 类型，或者 jdbc 类型，采用特定的处理器来处理这个类型的字段。
听起来这个概念很陌生，但实际上 MyBatis 本身内部就已经预置了好多好多类型处理器了

类型处理器     | JavaType | JdbcType
-------- | ----- | ----------
IntegerTypeHandler  | Integer 、int | int / numeric
DoubleTypeHandler  | Double 、double |  double / numberic
StringTypeHandler  | String |  varchar / char
DateTypeHandler  | Date |  timestamp
EnumTypeHandler  | Enum | varchar / char

一般情况下，我们只需要使用 MyBatis 内置的这些 typeHandler 就完全够用，如果实在是满足不了需求，也没关系，我们可以针对某些特定的类型，自定义 typeHandler 来处理。



### 定义TypeHandler实现类




## objectFactory 对象工厂


## plugins 插件


## environments 环境


## databaseIdProvider 数据库厂商标识


## mappers 映射器




# 加载配置文件原理解读



加载配置文件主要就这两行代码，如下：

```xml
InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
```

两行代码分别代表 xml的加载与解析



## 全局配置文件的加载

Java中加载文件，主要就两种方式：

- IO
- ClassLoader

Mybatis配置文件的加载是借助 ClassLoader 来做得， 如下源码解析：

```java
public static InputStream getResourceAsStream(String resource) throws IOException {
    return getResourceAsStream((ClassLoader)null, resource);
}

public static InputStream getResourceAsStream(ClassLoader loader, String resource) throws IOException {
    InputStream in = classLoaderWrapper.getResourceAsStream(resource, loader);
    if (in == null) {
        throw new IOException("Could not find resource " + resource);
    } else {
        return in;
    }
}
```

**Resources.getResourceAsStream** 并没有传classLoader参数， 是  ClassLoaderWrapper类里自己初始化的 classLoader，如下代码：

```java
ClassLoader[] getClassLoaders(ClassLoader classLoader) {
    return new ClassLoader[]{
        classLoader, 
        this.defaultClassLoader, 
        Thread.currentThread().getContextClassLoader(), 
        this.getClass().getClassLoader(), 
        this.systemClassLoader
    };
}
```

下面是实际利用ClassLoader加载全局配置文件的底层源码：

```java
InputStream getResourceAsStream(String resource, ClassLoader[] classLoader) {
    for (ClassLoader cl : classLoader) {
        if (null != cl) {
            // try to find the resource as passed
            InputStream returnValue = cl.getResourceAsStream(resource);
            // now, some class loaders want this leading "/", so we'll add it and try again if we didn't find the resource
            if (null == returnValue) {
                returnValue = cl.getResourceAsStream("/" + resource);
            }
            if (null != returnValue) {
                return returnValue;
            }
        }
    }
    return null;
}
```



## 解析配置文件

```java
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
```

继续如下：

```java
public SqlSessionFactory build(InputStream inputStream) {
    return this.build((InputStream)inputStream, (String)null, (Properties)null);
}
```



```java
public SqlSessionFactory build(InputStream inputStream, String environment, Properties properties) {
    try {
        XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);
        return build(parser.parse());
    } // catch finally ......
}
```

这里面用到的第一个底层核心组件，是 **`XMLConfigBuilder`** 。而这个  **`XMLConfigBuilder`**  ，继承了一个叫 **`BaseBuilder`** 的东西：

```java
public class XMLConfigBuilder extends BaseBuilder {
}
```



### BaseBuilder

`BaseBuilder` 顾名思义，它是一个基础的构造器，它的初始化需要传入 MyBatis 的全局配置对象 `Configuration` ：

```java
public abstract class BaseBuilder {
    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;
    protected final TypeHandlerRegistry typeHandlerRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
        this.typeHandlerRegistry = this.configuration.getTypeHandlerRegistry();
    }
    //省略。。。
}
```

最终 MyBatis 初始化完成后，所有的配置项、Mapper 、statement 都会存放到`Configuration`。

核心的处理逻辑并不在 `BaseBuilder` 中，回到实现类 `XMLConfigBuilder` 中。

### XMLConfigBuilder

#### 构造方法定义

```java
public class XMLConfigBuilder extends BaseBuilder {

    private boolean parsed;
    private final XPathParser parser;
    private String environment;
    private final ReflectorFactory localReflectorFactory = new DefaultReflectorFactory();
    
    public XMLConfigBuilder(InputStream inputStream, String environment, Properties props) {
        // 注意这里new了一个XPathParser
        this(new XPathParser(inputStream, true, props, new XMLMapperEntityResolver()), environment, props);
    }

    private XMLConfigBuilder(XPathParser parser, String environment, Properties props) {
        //注意这里 new Configuration()
        super(new Configuration());
        ErrorContext.instance().resource("SQL Mapper Configuration");
        this.configuration.setVariables(props);
        this.parsed = false;
        this.environment = environment;
        this.parser = parser;
    }
}
```

注意源码中，它重载的构造方法不再需要 `InputStream` ，而是构造了一个 **`XPathParser`** 。

#### 核心parse方法





















