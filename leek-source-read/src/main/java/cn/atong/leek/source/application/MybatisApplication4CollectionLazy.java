package cn.atong.leek.source.application;

import cn.atong.leek.domain.entity.Department;
import cn.atong.leek.source.mapper.DepartmentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: leek-atbatis
 * @description: 一对多关联查询-延迟加载
 * @author: atong
 * @create: 2021-07-18 18:54
 */
public class MybatisApplication4CollectionLazy {
    public static void main(String[] args) throws IOException {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        Department department = departmentMapper.findById(1L);
        System.out.println(department);
        System.out.println(department.getUsers());
    }
}
