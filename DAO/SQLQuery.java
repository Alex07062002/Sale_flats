package org.example.DAO;

import org.example.DBConnection.ConnectionPool;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.Logger;

public class SQLQuery{

    public void updateFromList(PreparedStatement statement, List<?> params) throws SQLException {
        for(int i = 0;i< params.size();i++){
            if (params.get(i) != null) {
                if (params.get(i) instanceof Integer)
                    statement.setInt(i + 1, Integer.parseInt(params.get(i).toString()));
                if (params.get(i) instanceof Double)
                    statement.setDouble(i + 1, Double.parseDouble(params.get(i).toString()));
                if (params.get(i) instanceof Date)
                    statement.setDate(i + 1, java.sql.Date.valueOf(params.get(i).toString()));
                if (params.get(i) instanceof String) statement.setString(i + 1, params.get(i).toString());
            } else {
                if (params.get(i) == null){
                    statement.setNull(i+1, Types.DATE);
                }
                 else throw new RuntimeException();
            }
        }
    }

    public <T> List<T> executeQuery(String query, StatementConsumer consumer, ExecuteAround<T> extractor) {
        Logger logger = Logger.getLogger(StackWalker.getInstance().getCallerClass().getName());
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            if (consumer != null) consumer.set(statement);
            ResultSet resultSet = statement.executeQuery();
            ConnectionPool.getInstance().returnConnection(connection);
            return extractor.output(resultSet);
        } catch (SQLException e) {
            logger.warning("Unable to get data: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public void executeUpdate(String query, StatementConsumer consumer){
        Logger logger = Logger.getLogger(StackWalker.getInstance().getCallerClass().getName());
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            consumer.set(statement);
            statement.executeUpdate();
            ConnectionPool.getInstance().returnConnection(connection);
        }catch (SQLException e) {
            logger.warning("Unable to get data: " + e.getMessage());
        }
    }

    public int executeCount(String query, StatementConsumer consumer) {
        Logger logger = Logger.getLogger(StackWalker.getInstance().getCallerClass().getName());
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            consumer.set(statement);
            ResultSet resultSet = statement.executeQuery();
            ConnectionPool.getInstance().returnConnection(connection);
            return resultSet.getInt(1); // TODO ???
        } catch (SQLException e) {
            logger.warning("Unable to get data: " + e.getMessage());
        }
        return -1;
    }
    }
