package cn.atong.leek.source.configuration;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: leek-atbatis
 * @description: 配置文件加载原理
 * @author: atong
 * @create: 2021-07-27 21:18
 */
public class ConfigurationLoadTheory {

    public static void main(String[] args) throws IOException {

        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);

    }
}
