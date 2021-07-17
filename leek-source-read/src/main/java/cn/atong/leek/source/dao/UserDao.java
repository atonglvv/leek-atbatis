package cn.atong.leek.source.dao;

import cn.atong.leek.domain.entity.User;

import java.util.List;

/**
 * @program: leek-atbatis
 * @description: Use Dao
 * @author: atong
 * @create: 2021-07-17 21:36
 */
public interface UserDao {

    List<User> findAll();

    User findById(Long id);
}
