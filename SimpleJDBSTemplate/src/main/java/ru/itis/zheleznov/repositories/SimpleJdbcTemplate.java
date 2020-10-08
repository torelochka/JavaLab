package ru.itis.zheleznov.repositories;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcTemplate {

    private final DataSource dataSource;

    public SimpleJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) {

        ResultSet resultSet = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            List<T> result = new ArrayList<>();

            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
    }

    public <T> int update(String sql, Object... args) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }
}
