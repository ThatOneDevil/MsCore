package me.thatonedevil.mscore.Events;

import me.thatonedevil.mscore.DataManager.CustomPlayer;
import me.thatonedevil.mscore.MsCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.sql.SQLException;
import java.util.Objects;

import static me.thatonedevil.mscore.MsCore.format;

public class LivesDeath implements Listener {

    private MsCore main;

    public LivesDeath(MsCore main) {
        this.main = main;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        try {
            CustomPlayer playerData = new CustomPlayer(main, player.getUniqueId());
            int lives = playerData.getLives();
            if (Objects.equals(playerData.getDeadValue(), "ALIVE")) {
                switch (lives) {
                    case 2:
                        playerData.setLives(1);
                        player.sendTitle(format("&c&l☠ You died"), format("&cYou now have &41 &clife left!"));
                        break;
                    case 1:
                        playerData.setLives(0);
                        playerData.setDeadValue("DEAD");
                        player.sendTitle(format("&c&l☠ You died"), null);
                        player.sendMessage(" ");
                        player.sendMessage(format("&cUnfortunately you cannot participate, if you are a faction leader please transfer your ownership!"));
                        player.sendMessage(" ");
                        player.setGameMode(GameMode.ADVENTURE);
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "god " + player.getName());
                        Bukkit.dispatchCommand(console, "vanish " + player.getName());
                        break;
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


