package me.thatonedevil.mscore.commands;

import me.thatonedevil.mscore.dataManager.CustomPlayer;
import me.thatonedevil.mscore.dataManager.PlayerManager;
import me.thatonedevil.mscore.MsCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

import static me.thatonedevil.mscore.MsCore.format;

public class RevivePlayer implements CommandExecutor {

    private MsCore main;

    public RevivePlayer(MsCore main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    PlayerManager playerManagerPlayer = new PlayerManager();
                    playerManagerPlayer.getCustomPlayer(target.getUniqueId());
                    try {
                        CustomPlayer playerData = new CustomPlayer(main, target.getUniqueId());
                        playerData.setLives(3);
                        playerData.setDeadValue("ALIVE");
                        target.setGameMode(GameMode.SURVIVAL);
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        Bukkit.dispatchCommand(console, "god " + target.getName());
                        Bukkit.dispatchCommand(console, "vanish " + target.getName());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    player.sendMessage(format("&cYou have revived &6" + player.getName()));

                }
                }

            }
        return false;
    }

}
