package me.thatonedevil.mscore.Commands;

import me.thatonedevil.mscore.DataManager.CustomPlayer;
import me.thatonedevil.mscore.DataManager.PlayerManager;
import me.thatonedevil.mscore.MsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class GetPlayerData implements CommandExecutor {

    private MsCore main;

    public GetPlayerData(MsCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    try {
                        CustomPlayer playerData = new CustomPlayer(main, target.getUniqueId());
                        String dead = playerData.getDeadValue();
                        int lives = playerData.getLives();
                        UUID uuid = playerData.getUuid();

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Data of " + target.getDisplayName() +  "\n &cUUID: " + uuid + "\n  &cLives " + lives + "\n  &cDead: " + dead));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
            return false;
        }
        return false;
    }

}
