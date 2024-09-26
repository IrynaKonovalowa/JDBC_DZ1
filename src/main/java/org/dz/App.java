package org.dz;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App
{
    private static final String INSERT_MESSAGE = "INSERT INTO notice(message, type, processed) VALUES(?,?,?)";

    public static void main( String[] args ) throws SQLException {
        System.out.println( "Hello World!" );
        try (Connection connection1 = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5438/dz1-db",
                "sa", "admin")) {

       List<Message> insertMessage = new ArrayList<>();
       Message message1 = new Message("New message from " + LocalDateTime.now(), "INFO", false);
       Message message2 = new Message("An error occured in " + LocalDateTime.now(), "WARN", false);
       insertMessage.add( message1 );
       insertMessage.add( message2 );
       Random rand = new Random();

            while(true){
               try(PreparedStatement statement = connection1.prepareStatement(INSERT_MESSAGE)){
             Message mes = insertMessage.get(rand.nextInt(insertMessage.size()));
             statement.setString(1, mes.getMessage());
             statement.setString(2, mes.getType());
             statement.setBoolean(3, mes.isProcessed());
             statement.execute();
             }
              Thread.sleep(1000);
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
    }
    }
}
