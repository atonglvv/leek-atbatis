package cn.atong.leek.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: leek-atbatis
 * @description: User Entity
 * @author: atong
 * @create: 2021-07-11 20:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Long id;
    private String name;
    private Integer age;

}
