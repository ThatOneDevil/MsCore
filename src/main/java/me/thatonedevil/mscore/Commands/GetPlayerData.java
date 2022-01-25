package me.thatonedevil.mscore.Commands;

import me.thatonedevil.mscore.DataManager.CustomPlayer;
import me.thatonedevil.mscore.MsCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class GetPlayerData implements CommandExecutor {

    private MsCore main;

    public GetPlayerData(MsCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                CustomPlayer playerData = new CustomPlayer(main, player.getUniqueId());
                String dead = playerData.getDeadValue();
                int lives = playerData.getLives();

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Data of " + player.getDisplayName() + "\n  &cLives " + lives + "\n  Dead: " + dead));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
