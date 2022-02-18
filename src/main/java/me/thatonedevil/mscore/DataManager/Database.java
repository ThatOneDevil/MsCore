package me.thatonedevil.mscore.DataManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String HOST = "";
    private final int PORT = 0;
    private final String DATABASE = "";
    private final String USERNAME = "";
    private final String PASSWORD = "";


    private Connection connection;


    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false",
                USERNAME,
                PASSWORD);
    }

    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() { return connection; }

    public boolean isConnected() {
        return connection != null;
    }

}
