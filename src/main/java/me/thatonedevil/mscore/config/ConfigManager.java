package me.thatonedevil.mscore.config;

import me.thatonedevil.mscore.MsCore;

public class ConfigManager{

    private MsCore main;

    public ConfigManager(MsCore main) {
        this.main = main;
    }

    public String getHost() {
        return main.getConfig().getString("Host");
    }

    public Integer getPort() {
        return main.getConfig().getInt("Port");
    }

    public String getDatabase() {
        return main.getConfig().getString("Database");
    }

    public String getUsername() {
        return main.getConfig().getString("Username");
    }

    public String getPassword() {
        return main.getConfig().getString("Password");
    }

}
