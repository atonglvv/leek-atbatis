package cn.atong.leek.atbatis;

import lombok.Data;

import java.util.Map;

/**
 * @program: leek-atbatis
 * @description: Xml解析对象
 * @author: atong
 * @create: 2021-07-15 21:52
 */
@Data
public class XNode {
    private String namespace;
    private String id;
    private String parameterType;
    private String resultType;
    private String sql;
    private Map<Integer, String> parameter;
}
