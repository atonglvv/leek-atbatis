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
