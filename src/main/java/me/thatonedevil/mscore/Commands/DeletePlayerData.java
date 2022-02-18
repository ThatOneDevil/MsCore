package me.thatonedevil.mscore.Commands;

import me.thatonedevil.mscore.DataManager.CustomPlayer;
import me.thatonedevil.mscore.DataManager.PlayerManager;
import me.thatonedevil.mscore.MsCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

import static me.thatonedevil.mscore.MsCore.format;

public class DeletePlayerData implements CommandExecutor {

    private MsCore main;

    public DeletePlayerData(MsCore main) {
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
                        playerData.deletePlayerData(target.getUniqueId());
                        player.sendMessage(format("&cDeleted &6" + target.getDisplayName() + "&c's Data!"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return false;
    }

}
