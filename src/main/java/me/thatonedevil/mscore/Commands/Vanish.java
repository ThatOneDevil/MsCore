package me.thatonedevil.mscore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Vanish implements CommandExecutor {
    private List<UUID> vanished = new ArrayList<>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 3) {
            if (Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);

                if (vanished.contains(target.getUniqueId())) {
                    vanished.remove(target.getUniqueId());
                    for (Player targetPlayer : Bukkit.getOnlinePlayers()) {
                        targetPlayer.showPlayer(targetPlayer);
                    }
                    target.sendMessage("You are now unvanished");

                } else {
                    vanished.add(target.getUniqueId());
                    for (Player targetPlayer : Bukkit.getOnlinePlayers()) {
                        targetPlayer.hidePlayer(targetPlayer);
                    }
                    target.sendMessage("You are not vanished");
                }
            }
        }


        return false;
    }
}
