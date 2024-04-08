package me.giveblock.gbshop.commands;

import me.giveblock.gbshop.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Shop implements CommandExecutor {

    public Shop(Main plugin) {}

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("shop")) {
                if (args.length == 0) {
                    player.sendMessage("test success");
                }

            }

        } else {
            sender.sendMessage("This command can only be used by players");
        }

        return false;
    }

}
