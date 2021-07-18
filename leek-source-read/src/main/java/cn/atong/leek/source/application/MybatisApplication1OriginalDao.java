package cn.atong.leek.source.application;

import cn.atong.leek.domain.entity.User;
import cn.atong.leek.source.dao.UserDao;
import cn.atong.leek.source.dao.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: leek-atbatis
 * @description: 基于原始Dao的方式实现增删改查
 * 基于原始Dao的方式 DaoImpl会有很多重复代码, 比如获取SqlSession [缺点]
 * @author: atong
 * @create: 2021-07-17 21:25
 */
public class MybatisApplication1OriginalDao {
    public static void main(String[] args) throws IOException {
        //构造 SqlSessionFactory
        InputStream xmlStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xmlStream);

        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        List<User> userList = userDao.findAll();
        userList.forEach(System.out::println);

    }
}
