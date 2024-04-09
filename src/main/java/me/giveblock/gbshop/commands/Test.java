package me.giveblock.gbshop.commands;

import me.giveblock.gbshop.GBShop;
import me.giveblock.gbshop.utils.NBT;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Test implements CommandExecutor {

    public Test(GBShop plugin) {}

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

        }

        sender.sendMessage("Test Command");

        return true;
    }

}
