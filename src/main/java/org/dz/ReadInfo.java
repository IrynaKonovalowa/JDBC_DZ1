package org.dz;

import java.sql.*;

public class ReadInfo {
    private static final String READ_MESSAGE = "Select id, message, type, processed from notice WHERE type= 'INFO' AND processed=false";
    private static final String DELETE_MESSAGE = "Delete from notice WHERE id = ?";

public static void main(String[] args) throws SQLException {
    System.out.println( "Hello World!" );
    try (Connection connection2 = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5438/dz1-db",
            "sa", "admin")) {
        try(PreparedStatement statement = connection2.prepareStatement(READ_MESSAGE)){
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            String message = resultSet.getString("message");
            String type = resultSet.getString("type");
            boolean processed = resultSet.getBoolean("processed");

            System.out.println(message + " " + type + " " + processed );
            try(PreparedStatement statement1 = connection2.prepareStatement(DELETE_MESSAGE)){
                statement1.setInt(1, resultSet.getInt("id"));
                statement1.execute();
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
}
}
