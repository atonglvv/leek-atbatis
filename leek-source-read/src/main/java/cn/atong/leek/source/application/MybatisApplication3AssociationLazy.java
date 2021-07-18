package cn.atong.leek.source.application;

import cn.atong.leek.domain.entity.User;
import cn.atong.leek.source.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: leek-atbatis
 * @description: 多对一关联查询-延迟加载
 * @author: atong
 * @create: 2021-07-18 18:34
 */
public class MybatisApplication3AssociationLazy {
    public static void main(String[] args) throws IOException {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAllUserWithDepLazy();
        userList.forEach(System.out::println);
    }
}
