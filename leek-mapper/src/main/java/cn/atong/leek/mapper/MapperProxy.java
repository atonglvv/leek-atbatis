package cn.atong.leek.mapper;


import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
                System.out.println("method = " + method.getName());
                System.out.println("args = " + Arrays.toString(args));

                Map<String, Object> nameArgMap = buildMethodArgNameMap(method, args);

                //获取Method上的@Select
                Select annotation = method.getAnnotation(Select.class);
                if (annotation != null) {
                    //获取@Select 的 value 属性值
                    String[] value = annotation.value();
                    String sql = value[0];
                    sql = parseSql(sql, nameArgMap);
                    System.out.println(sql);
                    //获取method返回值类型
                    System.out.println(method.getReturnType());
                    //获取method返回值, 可以获取到泛型
                    System.out.println(method.getGenericReturnType());
                }
                return null;
            }
        });

        userMapper.selectUserList(1, "atong");
    }

    public static Map<String,Object> buildMethodArgNameMap(Method method, Object[] args) {
        Map<String, Object> nameArgMap = new ConcurrentHashMap<>();
        Parameter[] parameters = method.getParameters();
        final int[] index = {0};
        //如果数据量很大的话, 可以用 parallelStream() , 但要注意 namArgMap 必须要用ConcurrentHashMap, 当然在这里没必要这样用, 提一嘴
        Arrays.asList(parameters).parallelStream().forEach(parameter -> {
            String name = parameter.getName();
            System.out.println(name);
            System.out.println(args[index[0]]);
            nameArgMap.put(name, args[index[0]]);
            index[0]++;
        });
        return nameArgMap;
    }



    /**
     * @description 解析sql, 参数填充
     * @param sql: String SQL
     * @param nameArgMap:  Map<String, Object>
     * @return java.lang.String
     * @author atong
     * @date 2021/7/11 22:41
     * @version 1.0.0.1
     */
    public static String parseSql(String sql, Map<String, Object> nameArgMap) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = sql.length();
        for (int i = 0; i < length; i++) {
            char c = sql.charAt(i);
            if (c == '#') {
                int nextIndex = i + 1;
                char nextChar = sql.charAt(nextIndex);
                if (nextChar != '{') {
                    throw new RuntimeException(String. format("这里应该为#{\nsql:%s index:%d",
                            stringBuilder.toString(),
                            nextIndex ));
                }
                StringBuilder argSB = new StringBuilder();
                i = parseSqlArg(argSB, sql, nextIndex);
                String argName = argSB.toString();
                Object argvalue = nameArgMap.get(argName);
                if (argvalue == null) {
                    throw new RuntimeException(String. format("找不到参数值%s",
                            stringBuilder.toString()));
                }
                stringBuilder. append(argvalue.toString());
                continue;
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    private static int parseSqlArg(StringBuilder argSB, String sql, int nextIndex) {
        nextIndex++;
        for (; nextIndex < sql.length(); nextIndex++) {
            char c = sql.charAt(nextIndex);
            if ( c != '}') {
                argSB.append(c);
            }else {
                return nextIndex;
            }
        }
        throw new RuntimeException(String. format("缺少右括号\nindex:%d", nextIndex ));
    }
}
