package me.thatonedevil.mscore.Commands;

import me.thatonedevil.mscore.DataManager.CustomPlayer;
import me.thatonedevil.mscore.MsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class SetPlayerData implements CommandExecutor {

    private MsCore main;

    public SetPlayerData(MsCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 3) {
                if (Bukkit.getPlayer(args[0]) != null) {

                    Player target = Bukkit.getPlayer(args[0]);
                    try {
                        CustomPlayer playerData = new CustomPlayer(main, target.getUniqueId());
                        switch (args[1].toLowerCase()) {
                            case "dead":
                                playerData.setDeadValue(args[2]);
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Set deadValue of &e" + target.getDisplayName() + " &6to &e" + args[2]));
                                break;
                            case "lives":
                                playerData.setLives(Integer.parseInt(args[2]));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Set lives of &e" + target.getDisplayName() + " &6to &e" + args[2].toUpperCase()));
                                break;
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/setplayer data [player] [dead, lives] [dead: True/False, Lives: Integer]"));
                }
            }
            return false;
        }
        return false;
    }
}