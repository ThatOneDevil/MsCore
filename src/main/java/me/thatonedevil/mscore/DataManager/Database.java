package me.thatonedevil.mscore.DataManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String HOST = "free-tier13.aws-eu-central-1.cockroachlabs.cloud";
    private final int PORT = 26257;
    private final String DATABASE = "testdb-280.defaultdb";
    private final String USERNAME = "thatonedevil";
    private final String PASSWORD = "C7E1bc9fcV0Opygw6LZwow";


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
