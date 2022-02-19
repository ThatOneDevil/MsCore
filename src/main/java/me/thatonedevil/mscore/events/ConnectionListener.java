package me.thatonedevil.mscore.events;

import me.thatonedevil.mscore.dataManager.CustomPlayer;
import me.thatonedevil.mscore.MsCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class ConnectionListener implements Listener{

    private MsCore main;

    public ConnectionListener(MsCore main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        try {
            CustomPlayer playerData = new CustomPlayer(main, e.getPlayer().getUniqueId());
            main.getPlayerManager().addCustomPlayer(e.getPlayer().getUniqueId(), playerData);
        } catch (SQLException ex) {
            ex.printStackTrace();
            e.getPlayer().kickPlayer("Your data could not be loaded");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        main.getPlayerManager().removeCustomPlayer(e.getPlayer().getUniqueId());
    }

}
