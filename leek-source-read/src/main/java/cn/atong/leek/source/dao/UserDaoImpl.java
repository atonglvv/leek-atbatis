package cn.atong.leek.source.dao;

import cn.atong.leek.domain.entity.User;
import org.apache.ibatis.session.SqlSession;
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
 *
 * But 原始Dao开发的弊端
 * 可以发现，两个方法的方法名不同、参数列表不同，调用的 mapper 不同，返回值不同，其余的几乎完全相同！
 * 我们也知道，更好地优化方案是使用 Mapper 动态代理的方式。
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


    /**
     * 使用 try-with-resource 的方式，可以省略 sqlSession.close(); 的代码
     * @return List<User>
     */
    @Override
    public List<User> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectList("userBaseMapper.selectUserAll");
        }
    }

    @Override
    public User findById(Long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectOne("userBaseMapper.findById", id);
        }
    }
}
