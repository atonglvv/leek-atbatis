package cn.atong.leek.source.mapper;

import cn.atong.leek.domain.entity.User;

import java.util.List;

/**
 * @program: leek-atbatis
 * @description: UserMapper
 * @author: atong
 * @create: 2021-07-18 12:30
 */
public interface UserMapper {

    List<User> findAllUserWithDep();

    List<User> findAllUserWithDepLazy();

    List<User> findAllByDepartmentId(Long id);

    List<User> findAll();

    int insert(User user);

    int update(User user);

    int deleteById(Long id);

    User findById(Long id);
}
