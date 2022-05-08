package cn.atong.leek.tear.abatis.test;


import cn.atong.leek.tear.abatis.MapperProxyFactory;
import cn.atong.leek.tear.abatis.test.dao.IUserDao;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @description  单元测试
 * @author atong
 * @date 16:48 2022/5/8
 * @version 1.0.0.1
 **/
public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_MapperProxyFactory() {
        
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);

        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("cn.atong.leek.tear.abatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("cn.atong.leek.tear.abatis.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");
        IUserDao userDao = factory.newInstance(sqlSession);

        userDao.toString();

        String res = userDao.queryUserName("10001");
        logger.info("测试结果：{}", res);
    }

}
