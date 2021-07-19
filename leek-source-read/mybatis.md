# mybatis

## mybatis 执行流程

mybatis执行流程.png

### 执行流程解释

#### 接口代理 Mapper
与Mybatis交互的门面, 存在的目的是为了方便调用, 本身不会影响执行逻辑。

#### SqlSession
所有对数据库的操作必须经过他, 但他不会真正去执行业务逻辑, 而是交给Executor。
另外他不是线程安全的, 所以不能跨线程调用。

#### Executor
真正执行业务逻辑的组件, 具体职责包括JDBC交互, 缓存管理, 事务管理等。

