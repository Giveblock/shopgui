package me.giveblock.gbshop.api.helpers;

import me.giveblock.gbshop.commands.Shop;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ShopHelper {

    private static DecimalFormat dfDec = new DecimalFormat("#.##");
    private static DecimalFormat dfNum = new DecimalFormat("#");

    public static ItemStack shopSkull(Player p) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setDisplayName(p.getDisplayName());
        skullMeta.setOwningPlayer(p);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§f- §aBalance§f: ");
        skullMeta.setLore(lore);


        item.setItemMeta(skullMeta);
        return item;
    }

    public static ItemStack compass() {
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§c§lGo Back");
        item.setItemMeta(meta);

        return item;
    }

    public static String formatPrice(double price) {
        if (price%1 != 0 || price < 1) {
            String s = dfDec.format(price);
            if (s.endsWith(".5")) {
               s = s.concat("0");
            }
            return s;
        } else {
            return dfNum.format(price);
        }

    }

}
