package me.thatonedevil.mscore.dataManager;

import me.thatonedevil.mscore.MsCore;
import me.thatonedevil.mscore.config.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    final ConfigManager configManager;
    final String host, database, username, password;
    final int port;
    public Database(MsCore main) {
        configManager = new ConfigManager(main);
        host = configManager.getHost();
        port = configManager.getPort();
        database = configManager.getDatabase();
        username = configManager.getUsername();
        password = configManager.getPassword();
    }

    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false",
                username,
                password);
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
