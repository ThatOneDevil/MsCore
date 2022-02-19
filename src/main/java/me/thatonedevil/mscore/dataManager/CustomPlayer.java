package me.thatonedevil.mscore.dataManager;

import me.thatonedevil.mscore.MsCore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomPlayer {

    private MsCore main;

    private int lives;
    private String dead;

    private UUID uuid;
    public CustomPlayer(MsCore main, UUID uuid) throws SQLException {
        this.main = main;

        this.uuid = uuid;

        PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("SELECT LIVES, DEAD FROM players WHERE UUID = ?;");
        statement.setString(1, uuid.toString());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            lives = rs.getInt("LIVES");
            dead = rs.getString("DEAD");
        } else {
            lives = 2;
            dead = "ALIVE";
            PreparedStatement statement1 = main.getDatabase().getConnection().prepareStatement("INSERT INTO players (ID, UUID, LIVES, DEAD) VALUES (" +
                    "DEFAULT," +
                    "'" + uuid + "'," +
                    lives + "," +
                    "'" + dead + "');");
            statement1.executeUpdate();
        }

    }

    public void setLives(int lives) {
        this.lives = lives;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("UPDATE players SET LIVES = " + lives + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDeadValue(String dead) {
        this.dead = dead;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("UPDATE players SET DEAD = '" + dead + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlayerData(UUID uuid) {
        this.uuid = uuid;
        try {
            PlayerManager playerManager = new PlayerManager();
            playerManager.removeCustomPlayer(uuid);
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement("DELETE from players WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        };
    }

    public int getLives() { return lives; }
    public String getDeadValue() { return dead; }
    public UUID getUuid() { return uuid; }


}
