package org.example.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ExecuteAround<T> {
    List<T> output(ResultSet resultSet) throws SQLException;
}
