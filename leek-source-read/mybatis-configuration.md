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

