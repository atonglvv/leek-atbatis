package cn.atong.leek.atbatis;

import java.util.List;

/**
 * @program: leek-atbatis
 * @description: SqlSession 定义对数据库操作的查询接口
 * 门面模式： 提供一个统一的门面接口API, 使得系统更容易使用
 * 提供基本 增删改查 Api  与  辅助API(提交/关闭会话)
 * @author: atong
 * @create: 2021-07-14 22:33
 */
public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object parameter);

    <T> List<T> selectList(String statement);

    <T> List<T> selectList(String statement, Object parameter);

    void close();
}
