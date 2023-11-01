package org.bill.demoproject.common.typehandler;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.bill.demoproject.common.utils.JsonUtils;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
public abstract class ListTypeHandler<T> extends BaseTypeHandler<List<T>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JsonUtils.toJson(parameter));
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        System.out.println("getNullableResult");
        System.out.println(rs.getString(columnName));
        return rs.wasNull() ? null : JsonUtils.readList(rs.getString(columnName), specificType());
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        System.out.println("getNullableResult");
        System.out.println(rs.getString(columnIndex));
        return rs.wasNull() ? null : JsonUtils.readList(rs.getString(columnIndex), specificType());
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        System.out.println("getNullableResult");
        System.out.println(cs.getString(columnIndex));
        return cs.wasNull() ? null : JsonUtils.readList(cs.getString(columnIndex), specificType());
    }

    protected abstract TypeReference<List<T>> specificType();
}
