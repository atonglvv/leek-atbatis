package cn.atong.leek.tear.abatis.test.dao;

/**
 * @description  User DAO
 * @author atong
 * @date 16:03 2022/5/8
 * @version 1.0.0.1
 **/
public interface IUserDao {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);

}
