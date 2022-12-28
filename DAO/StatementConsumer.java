package org.example.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementConsumer {

    void set(PreparedStatement statement) throws SQLException;
}

