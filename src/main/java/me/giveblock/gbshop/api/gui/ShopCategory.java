package me.giveblock.gbshop.api.gui;

import com.google.gson.JsonObject;
import me.giveblock.gbshop.api.helpers.ShopHelper;
import me.giveblock.gbshop.utils.FileSystem;
import me.giveblock.gbshop.utils.NBT;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopCategory {

    public static Inventory gui(Player p, String category, int page) {
        Inventory gui = Bukkit.createInventory(null, 54, "§8§lShopGUI §8- §lCategory");
        getShopPage(gui, p, category, page);

        return gui;
    }

    public static void onClick(InventoryClickEvent e) {
        Inventory inv = e.getClickedInventory();
        Player p = (Player) e.getWhoClicked();

        int slot = e.getSlot();
        assert inv != null;
        ItemStack item = inv.getItem(slot);


        //Toolbar
        if (slot == 45) {
            p.openInventory(ShopHome.gui(p));
        }
        if (slot == 48) {
            String category = NBT.getString(item, "category");
            int page = NBT.getInt(item, "prevPage");
            p.openInventory(gui(p, category, page));
        }
        if (slot == 50) {
            String category = NBT.getString(item, "category");
            int page = NBT.getInt(item, "nextPage");
            p.openInventory(gui(p, category, page));
        }


    }


    private static void getShopPage(Inventory inv, Player p, String category, int page) {
        getToolbar(inv, p, category, page);
        getShopItems(inv, category, page);
    }

    private static void getShopItems(Inventory inv, String category, int page) {
        JsonObject items = FileSystem.shop.getAsJsonObject("categories").getAsJsonObject(category).getAsJsonObject("items");
        int size = items.size();
        String[] names = items.keySet().toArray(new String[size]);

        int start = 45*page;
        int end = start + 45;
        if (end > size) {
            end = size;
        }

        for (int i = start; i < end; i++) {
            String name = names[i];
            ItemStack item = shopItem(name, items.getAsJsonObject(name));
            if (item != null) {
                inv.addItem(item);
            }
        }
    }
    private static ItemStack shopItem(String name, JsonObject itemInfo) {
        Material material = Material.matchMaterial(name);
        if (material == null) {Bukkit.getLogger().info(name.toUpperCase() + " WAS SHOP ERROR");}
        if (material != null) {
            ItemStack item = new ItemStack(material);
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


        return null;
    }

    private static void getToolbar(Inventory inv, Player p, String category, int page) {
        inv.setItem(45, ShopHelper.compass());

        JsonObject items = FileSystem.shop.getAsJsonObject("categories").getAsJsonObject(category).getAsJsonObject("items");
        int size = items.size();
        int pages = size / 45;

        int prevPage = page - 1;
        if (prevPage < 0) {
            prevPage = pages;
        }
        inv.setItem(48, prevButton(prevPage, category));

        inv.setItem(49, getCategoryBook(page + 1, pages + 1));


        int nextPage = page + 1;
        if (nextPage > pages) {
            nextPage = 0;
        }
        inv.setItem(50, nextButton(nextPage, category));

        inv.setItem(53, catSkull(p, category));
    }
    private static ItemStack catSkull(Player p, String category) {
        ItemStack skull = ShopHelper.shopSkull(p);
        return NBT.addTag(skull, "category", category);
    }
    private static ItemStack getCategoryBook(int page, int pages) {
        ItemStack book = new ItemStack(Material.BOOK);
        book = NBT.addTag(book,"page", page);
        ItemMeta meta = book.getItemMeta();
        meta.setDisplayName("§aPage " + page + "/" + pages);
        book.setItemMeta(meta);

        return book;
    }
    private static ItemStack prevButton(int prevPage, String category) {
        ItemStack item = new ItemStack(Material.STONE_BUTTON);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cPrevious Page");
        item.setItemMeta(meta);
        item = NBT.addTag(item,"category", category);

        return NBT.addTag(item, "prevPage", prevPage);
    }
    private static ItemStack nextButton(int nextPage, String category) {
        ItemStack item = new ItemStack(Material.STONE_BUTTON);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aNext Page");
        item.setItemMeta(meta);
        item = NBT.addTag(item, "category", category);

        return NBT.addTag(item, "nextPage", nextPage);
    }


}
