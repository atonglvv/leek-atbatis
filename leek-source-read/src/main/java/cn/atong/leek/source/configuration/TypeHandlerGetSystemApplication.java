package cn.atong.leek.source.configuration;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * @program: leek-atbatis
 * @description: 查看Mybatis 所有的TypeHandler
 * @author: atong
 * @create: 2021-07-26 22:14
 */
public class TypeHandlerGetSystemApplication {
    public static void main(String[] args) throws IOException {
        //获取sqlSession
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取 TypeHandlerRegistry
        TypeHandlerRegistry typeHandlerRegistry = sqlSession.getConfiguration().getTypeHandlerRegistry();
        //获取 typeHandlers
        Collection<TypeHandler<?>> typeHandlers = typeHandlerRegistry.getTypeHandlers();
        System.out.println("TypeHandlers size  = " + typeHandlers.size());
        typeHandlers.forEach( typeHandler -> {
            System.out.println(typeHandler.getClass().getName());
        });
    }
}
