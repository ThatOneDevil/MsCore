package me.thatonedevil.mscore;

import me.thatonedevil.mscore.commands.*;
import me.thatonedevil.mscore.dataManager.Database;
import me.thatonedevil.mscore.dataManager.PlayerManager;
import me.thatonedevil.mscore.events.ConnectionListener;
import me.thatonedevil.mscore.events.ItemPickup;
import me.thatonedevil.mscore.events.LivesDeath;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;


public final class MsCore extends JavaPlugin {

    private Database database;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Database Database = new Database(this);

        database = new Database(this);
        playerManager = new PlayerManager();

        try {
            database.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("-----------------");
        if (database.isConnected()) {
            System.out.println("Database connection: " + database.isConnected() + " This is a good thing!");
        } else {
            System.out.println("Database connection: " + database.isConnected() + " This is a bad thing!");
        }
        System.out.println();
        System.out.println("-----------------");

        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new LivesDeath(this), this);
        Bukkit.getPluginManager().registerEvents(new ItemPickup(this), this);

        getCommand("getPlayerData").setExecutor(new GetPlayerData(this));
        getCommand("setPlayerData").setExecutor(new SetPlayerData(this));
        getCommand("resetPlayerData").setExecutor(new DeletePlayerData(this));
        getCommand("revivePlayer").setExecutor(new RevivePlayer(this));
        getCommand("msVanish").setExecutor(new Vanish());
        getCommand("ResetAllData").setExecutor(new ResetAllData(this));
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

