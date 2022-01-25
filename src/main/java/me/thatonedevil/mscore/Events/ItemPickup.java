package me.thatonedevil.mscore.Events;

import me.thatonedevil.mscore.DataManager.CustomPlayer;
import me.thatonedevil.mscore.MsCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import java.sql.SQLException;
import java.util.Objects;

public class ItemPickup implements Listener{

    private MsCore main;

    public ItemPickup(MsCore main) {
        this.main = main;
    }

    @EventHandler
    public void onCollect(EntityPickupItemEvent e) {
        Player player = (Player) e.getEntity();
        try {
            CustomPlayer playerData = new CustomPlayer(main, player.getUniqueId());
            main.getPlayerManager().addCustomPlayer(player.getUniqueId(), playerData);
            if (Objects.equals(playerData.getDeadValue(), "DEAD")) {
                e.setCancelled(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
