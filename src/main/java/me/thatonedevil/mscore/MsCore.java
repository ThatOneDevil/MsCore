package me.thatonedevil.mscore;

import me.thatonedevil.mscore.Commands.DeletePlayerData;
import me.thatonedevil.mscore.Commands.GetPlayerData;
import me.thatonedevil.mscore.Commands.RevivePlayer;
import me.thatonedevil.mscore.Commands.SetPlayerData;
import me.thatonedevil.mscore.DataManager.Database;
import me.thatonedevil.mscore.DataManager.PlayerManager;
import me.thatonedevil.mscore.Events.ConnectionListener;
import me.thatonedevil.mscore.Events.ItemPickup;
import me.thatonedevil.mscore.Events.LivesDeath;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;


public final class MsCore extends JavaPlugin {

    private Database database;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        database = new Database();
        playerManager = new PlayerManager();
        try {
            database.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Database Connected: " + database.isConnected());

        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new LivesDeath(this), this);
        Bukkit.getPluginManager().registerEvents(new ItemPickup(this), this);
        getCommand("getPlayerData").setExecutor(new GetPlayerData(this));
        getCommand("setPlayerData").setExecutor(new SetPlayerData(this));
        getCommand("resetPlayerData").setExecutor(new DeletePlayerData(this));
        getCommand("revivePlayer").setExecutor(new RevivePlayer(this));
    }
    @Override
    public void onDisable() {
        database.disconnect();
    }

    public Database getDatabase() { return database; }
    public PlayerManager getPlayerManager() { return playerManager; }

    public static String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}

