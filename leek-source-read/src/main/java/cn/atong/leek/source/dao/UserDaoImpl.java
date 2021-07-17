package cn.atong.leek.source.dao;

import cn.atong.leek.domain.entity.User;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @program: leek-atbatis
 * @description: UserDao 实现类
 * 问题来了，DepartmentDaoImpl 如何拿到 SqlSession 呢？
 * 肯定是借助 SqlSessionFactory 了吧。
 * 但 SqlSessionFactory 从哪来呢？
 * 肯定不可能我们自己创建吧，通常情况下一个工程在运行期只允许存在一个 SqlSessionFactory ，那应该怎么办呢？
 * 用构造方法注入就可以吧！或者 setter 方法注入进 DepartmentDaoImpl 都可以。
 * @author: atong
 * @create: 2021-07-17 21:51
 */
public class UserDaoImpl implements UserDao{

    private SqlSessionFactory sqlSessionFactory;

    /**
     * 构造器注入
     * @param sqlSessionFactory SqlSessionFactory
     */
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
