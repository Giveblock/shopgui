package me.giveblock.gbshop.api.helpers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemHelper {

    public static ItemStack shopSkull(Player p) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(p.getDisplayName());
        meta.setOwningPlayer(p);
        item.setItemMeta(meta);
        return item;
    }


}
