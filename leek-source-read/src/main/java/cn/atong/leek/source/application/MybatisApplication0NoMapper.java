package cn.atong.leek.source.application;

import cn.atong.leek.domain.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: leek-atbatis
 * @description: Mybatis Application
 * @author: atong
 * @create: 2021-07-17 19:24
 */
public class MybatisApplication0NoMapper {
    public static void main(String[] args) throws IOException {

        InputStream xmlStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xmlStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //namespace.statementId
        List<User> userList = sqlSession.selectList("userBaseMapper.selectUserAll");
        userList.forEach(System.out::println);

    }
}
