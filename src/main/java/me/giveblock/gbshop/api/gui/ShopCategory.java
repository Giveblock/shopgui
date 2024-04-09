package me.giveblock.gbshop.api.gui;

import me.giveblock.gbshop.utils.NBT;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopCategory {

    public static Inventory gui(Player p, ItemStack item) {
        String name = item.getItemMeta().getDisplayName();
        Inventory gui = Bukkit.createInventory(null, 54, "§lShopGUI - " + name);
        String category = NBT.getString(item, "category");

        return gui;
    }


}
