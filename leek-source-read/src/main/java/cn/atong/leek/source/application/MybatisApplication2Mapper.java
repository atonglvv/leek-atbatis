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
 * @description: 基于Mapper动态代理的开发方式
 *
 * 使用 Mapper 动态代理的方式开发，需要满足以下几个规范：
 * mapper.xml 中的 namespace 与 Mapper 接口的全限定名完全相同
 * mapper.xml 中定义的 statement ，其 id 与 Mapper 接口的方法名一致
 * Mapper 接口方法的方法参数类型，与 mapper.xml 中定义的 statement 的 parameterType 类型一致
 * Mapper 接口方法的返回值类型，与 mapper.xml 中定义的 statement 的 resultType 类型相同
 *
 * @author: atong
 * @create: 2021-07-18 12:05
 */
public class MybatisApplication2Mapper {
    public static void main(String[] args) throws IOException {
        InputStream xmlStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xmlStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取Mapper接口的代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findById(2L);
        System.out.println(user);
    }
}
