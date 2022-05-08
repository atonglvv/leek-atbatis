package cn.atong.leek.tear.abatis;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @program: leek-atbatis
 * @description: 通过代理类包装对数据库的操作
 * @author: atong
 * @create: 2022-05-08 15:59
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;

    /**
     * key -> Mapper接口名称+方法名称
     *
     */
    private Map<String, String> sqlSession;
    /**
     * 被代理的接口
     */
    private final Class<T> mapperInterface;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            // Object 提供的 toString、hashCode 等方法是不需要代理的
            return method.invoke(this, args);
        } else {
            return "你被代理了！" + sqlSession.get(mapperInterface.getName() + "." + method.getName());
        }
    }
}
