package cn.atong.leek.source.configuration;

import cn.atong.leek.domain.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @program: leek-atbatis
 * @description: LoadPropertiesApplication
 * Properties加载多个properties文件到一个对象中
 * 如果真的需要一次性加载多个外部化配置文件，使用 xml 配置的方式是无法实现的。
 * 不过我们可以换一种思路来实现：
 * Properties 这个类是可以加载多个 properties 文件到一个对象中的，所以我们可以基于这个思路来实现。
 * @author: atong
 * @create: 2021-07-19 23:29
 */
public class LoadPropertiesApplication {
    public static void main(String[] args) throws IOException {

        InputStream xmlStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 使用Properties的API加载这些properties文件
        Properties properties = new Properties();
        properties.load(Resources.getResourceAsStream("jdbc1.properties"));
        properties.load(Resources.getResourceAsStream("jdbc2.properties"));

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xmlStream, properties);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> userList = sqlSession.selectList("userBaseMapper.selectUserAll");
        userList.forEach(System.out::println);
    }
}
