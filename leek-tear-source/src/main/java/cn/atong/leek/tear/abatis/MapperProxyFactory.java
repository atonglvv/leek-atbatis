package cn.atong.leek.tear.abatis;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @program: leek-atbatis
 * @description: 简单工厂
 * 如果不做这层封装,那么每一个创建代理类的操作,都需要自己使用 Proxy.newProxyInstance 进行处理, 这样的操作方式就显得比较麻烦
 * @author: atong
 * @create: 2022-05-08 16:22
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
