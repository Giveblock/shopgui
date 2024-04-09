package me.giveblock.gbshop.commands;

import me.giveblock.gbshop.GBShop;
import me.giveblock.gbshop.api.gui.ShopHome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Shop implements CommandExecutor {

    public Shop(GBShop plugin) {}

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("shop")) {
                if (args.length == 0) {
                    player.openInventory(ShopHome.gui(player));
                    return true;
                }

            }

        } else {
            sender.sendMessage("This command can only be used by players");
            return true;
        }

        return false;
    }

}
