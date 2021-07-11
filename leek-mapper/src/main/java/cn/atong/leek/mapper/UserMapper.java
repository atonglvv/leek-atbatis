package cn.atong.leek.mapper;

import cn.atong.leek.domain.entity.User;

import java.util.List;

/**
 * @program: leek-atbatis
 * @description: Mapper Interface
 * @author: atong
 * @create: 2021-07-11 21:22
 */
public interface UserMapper {
    List<User> selectUserList();
}
