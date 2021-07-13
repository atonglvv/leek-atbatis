package cn.atong.leek.mapper;

import cn.atong.leek.domain.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: leek-atbatis
 * @description: Mapper Interface
 * @author: atong
 * @create: 2021-07-11 21:22
 */
public interface UserMapper {
    @Select("select * from user where id = #{id} and name = #{name}")
    List<User> selectUserList(Integer id, String name);
}
