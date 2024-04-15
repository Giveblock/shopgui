package me.giveblock.gbshop.api.gui;

import com.google.gson.JsonObject;
import me.giveblock.gbshop.api.helpers.ShopHelper;
import me.giveblock.gbshop.utils.FileSystem;
import me.giveblock.gbshop.utils.NBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopCategory {

    public static Inventory gui(Player p, ItemStack item) {
        String name = item.getItemMeta().getDisplayName();
        Inventory gui = Bukkit.createInventory(null, 54, "§lShopGUI - " + name);
        String category = NBT.getString(item, "category");


        getToolbar(gui, p, category);

        getShopItems(gui, category);

        return gui;
    }


    private static void getShopItems(Inventory inv, String category) {
        JsonObject items = FileSystem.shop.getAsJsonObject("categories").getAsJsonObject(category).getAsJsonObject("items");
        int size = items.size();
        String[] names = items.keySet().toArray(new String[size]);

        if (size <= 45) {
            for (int i = 0; i < size; i++) {
                String name = names[i];
                inv.addItem(shopItem(name, items.getAsJsonObject(name)));

            }

        }



    }

    private static ItemStack shopItem(String name, JsonObject itemInfo) {
        ItemStack item = new ItemStack(Material.matchMaterial(name));
        ItemMeta meta = item.getItemMeta();

        double buy = itemInfo.get("buy").getAsDouble();
        double sell = itemInfo.get("sell").getAsDouble();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§f- §7Buy§f: §f$§a" + ShopHelper.formatPrice(buy));
        lore.add("§f- §7Sell§f: §f$§c" + ShopHelper.formatPrice(sell));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }




    private static void getToolbar(Inventory inv, Player p, String category) {
        inv.setItem(45, ShopHelper.compass());






        inv.setItem(53, catSkull(p, category));
    }
    private static ItemStack catSkull(Player p, String category) {
        ItemStack skull = ShopHelper.shopSkull(p);
        return NBT.addTag(skull, "category", category);
    }





}
