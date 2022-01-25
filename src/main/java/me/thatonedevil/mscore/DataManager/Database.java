package me.thatonedevil.mscore.DataManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String HOST = "sql4.freesqldatabase.com";
    private final int PORT = 3306;
    private final String DATABASE = "sql4467828";
    private final String USERNAME = "sql4467828";
    private final String PASSWORD = "P91b5swTQ2";


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
