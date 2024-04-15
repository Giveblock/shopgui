package me.giveblock.gbshop.api.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.giveblock.gbshop.api.helpers.ShopHelper;
import me.giveblock.gbshop.utils.FileSystem;
import me.giveblock.gbshop.utils.NBT;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopHome {

    public static Inventory gui(Player p) {
        int size = FileSystem.shop.getAsJsonObject("shop-home").get("gui-size").getAsInt();
        Inventory gui = Bukkit.createInventory(null, size, "§lShopGUI §8- §lHome");

        getItems(gui, p);

        return gui;

    }

    private static void getItems(Inventory inv, Player p) {
        JsonObject categories = FileSystem.shop.getAsJsonObject("categories");
        for (String category : categories.keySet()) {
            categoryItem(categories, category, inv);
        }

        int skullSlot = FileSystem.shop.getAsJsonObject("shop-home").get("skull-slot").getAsInt();
        inv.setItem(skullSlot, ShopHelper.shopSkull(p));

    }
    private static void categoryItem(JsonObject categories, String category, Inventory inv) {
        JsonObject o = categories.getAsJsonObject(category).getAsJsonObject("menu-item");
        int slot = o.get("slot").getAsInt();
        Material material = Material.matchMaterial(o.get("id").getAsString().toUpperCase());
        String name = o.get("name").getAsString();
        name = name.replace("&", "§");
        JsonArray lore = o.getAsJsonArray("lore");

        NBTTagCompound tag = new NBTTagCompound();
        NBT.addTag(tag, "type", "category");
        NBT.addTag(tag, "category", category);

        ItemStack item = NBT.getItem(tag, new ItemStack(material));
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
        inv.setItem(slot, item);

    }


    public static void onClick(InventoryClickEvent e) {
        Inventory inv = e.getClickedInventory();
        int slot = e.getSlot();
        assert inv != null;
        ItemStack item = inv.getItem(slot);
        String type = NBT.getString(item, "type");
        if (type.equalsIgnoreCase("category")) {
            String category = NBT.getString(item, "category");
            Player p = (Player) e.getWhoClicked();
            p.openInventory(ShopCategory.gui(p, item));
        }
    }

}
