package ru.itis.zheleznov.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    public T mapRow(ResultSet row) throws SQLException;
}
