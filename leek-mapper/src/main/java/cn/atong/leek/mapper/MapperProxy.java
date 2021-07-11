package cn.atong.leek.mapper;


import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: leek-atbatis
 * @description:
 * @author: atong
 * @create: 2021-07-11 21:22
 */
public class MapperProxy {
    public static void main(String[] args) {
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(MapperProxy.class.getClassLoader(), new Class<?>[]{UserMapper.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //输出 methodName
                System.out.println(method.getName());
                //获取
                Select annotation = method.getAnnotation(Select.class);
                if (annotation != null) {
                    String[] value = annotation.value();
                }
                return null;
            }
        });

        userMapper.selectUserList();
    }
}
