package cn.atong.leek.domain.entity;

import lombok.Data;

import java.util.Set;

/**
 * @program: leek-atbatis
 * @description:
 * @author: atong
 * @create: 2021-07-18 17:28
 */
@Data
public class Department {
    private Long id;
    private String name;
    private String tel;
    private Set<User> users;
}
