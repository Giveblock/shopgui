package me.giveblock.gbshop.api.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.giveblock.gbshop.utils.FileSystem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShopHome {

    public static Inventory gui(Player p) {
        int size = FileSystem.shop.get("gui-size").getAsInt();
        Inventory gui = Bukkit.createInventory(null, size, "§lShopGUI §8- §lHome");

        getItems(gui, p);

        return gui;

    }


    private static void getItems(Inventory inv, Player p) {
        JsonObject categories = FileSystem.shop.getAsJsonObject("categories");
        for (String key : categories.keySet()) {
            JsonObject category = categories.getAsJsonObject(key);
            int slot = category.get("slot").getAsInt();
            inv.setItem(slot, categoryItem(category.getAsJsonObject("menu-item")));
        }

    }

    private static ItemStack categoryItem(JsonObject o) {
        String material = o.get("id").getAsString();
        String name = o.get("name").getAsString();
        name = name.replace("&", "§");
        JsonArray lore = o.getAsJsonArray("lore");

        ItemStack item = new ItemStack(Material.matchMaterial(material.toUpperCase()));
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        if (!lore.isEmpty()) {
            ArrayList<String> loreList = new ArrayList<>();
            for (JsonElement element : lore) {
                String line = element.getAsString();
                loreList.add(line);
            }
            meta.setLore(loreList);

        }
        item.setItemMeta(meta);
        return item;
    }

}
