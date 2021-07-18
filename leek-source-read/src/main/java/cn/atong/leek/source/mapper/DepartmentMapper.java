package cn.atong.leek.source.mapper;

import cn.atong.leek.domain.entity.Department;
import cn.atong.leek.domain.entity.User;

import java.util.List;

/**
 * @program: leek-atbatis
 * @description: Department Mapper
 * @author: atong
 * @create: 2021-07-18 17:40
 */
public interface DepartmentMapper {

    List<Department> findAll();

    int insert(Department department);

    int update(Department department);

    int deleteById(Long id);

    Department findById(Long id);
}
