package cn.atong.leek.source.typehandler;

import cn.atong.leek.domain.entity.Department;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: leek-atbatis
 * @description:
 * TypeHandlers-类型处理器
 * 自己定义的 TypeHandler , 都需要实现 TypeHandler 接口, 并声明其泛型, 泛型就是要处理的目标类型.
 * @author: atong
 * @create: 2021-07-26 21:21
 */
public class DepartmentTypeHandler implements TypeHandler<Department> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Department department, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, department.getId());
    }

    @Override
    public Department getResult(ResultSet rs, String columnName) throws SQLException {
        Department department = new Department();
        department.setId(rs.getLong(columnName));
        return department;
    }

    @Override
    public Department getResult(ResultSet rs, int columnIndex) throws SQLException {
        Department department = new Department();
        department.setId(rs.getLong(columnIndex));
        return department;
    }

    @Override
    public Department getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Department department = new Department();
        department.setId(cs.getLong(columnIndex));
        return department;
    }
}
