package cn.atong.leek.jdbc;

import cn.atong.leek.jdbc.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: leek-atbatis
 * @description:
 * @author: atong
 * @create: 2021-07-10 17:50
 */
public class Application {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        List<User> users = new ArrayList<User>();

        try {
            // 1、加载数据库驱动（ 成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 2、获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://192.168.2.117:3306/draft", "root", "1230");
            // 3、获取数据库操作对象
            statement = connection.createStatement();
            // 4、定义操作的SQL语句  执行数据库操作
            resultSet = statement.executeQuery("select * from user");
            // 5、获取并操作结果集
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                Integer age = resultSet.getInt(3);
                users.add(new User(id, name, age));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7、关闭对象，回收数据库资源
            //关闭结果集对象
            if (resultSet != null) {
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 关闭数据库操作对象
            if (statement != null) {
                try {
                    statement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 关闭数据库连接对象
            if (connection != null) {
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(users.toString());
    }
}
