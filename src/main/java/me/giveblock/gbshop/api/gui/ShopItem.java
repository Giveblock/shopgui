package me.giveblock.gbshop.api.gui;

import me.giveblock.gbshop.api.helpers.ShopHelper;
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

public class ShopItem {

    public static Inventory gui(Player p, ItemStack item) {
        Inventory gui = Bukkit.createInventory(null, 9, "§8§lShopGUI §8- §8§lItem");

        getItems(gui, p, item);

        return gui;
    }

    public static void onClick(InventoryClickEvent e) {
        Inventory inv = e.getClickedInventory();
        Player p = (Player) e.getWhoClicked();

        int slot = e.getSlot();
        assert inv != null;
        ItemStack item = inv.getItem(slot);

        //Compass - Slot 0
        if (slot == 0) {
            String category = NBT.getString(item, "category");
            int page = NBT.getInt(item, "page");
            p.openInventory(ShopCategory.gui(p, category, page));
        }

        //Red Glass - Slot 1-3
        if (slot >= 1 && slot <= 3) {
            int count = NBT.getInt(item, "count");
            minusGlass(inv, p, count);
        }

        //Shop Item - Slot 4
        if (slot == 4) {

        }

        //Green Glass - Slot 5-7
        if (slot >= 5 && slot <= 7) {
            int count = NBT.getInt(item, "count");
            plusGlass(inv, p, count);
        }

        //Player Head - Slot 8


    }

    private static void getItems(Inventory inv, Player p, ItemStack item) {
        inv.setItem(1, glass("Set to 1", 0, true));
        inv.setItem(2, glass("-10", 10, true));
        inv.setItem(3, glass("-1", 1, true));

        inv.setItem(4, getShopItem(item, 1, p));

        inv.setItem(5, glass("+1", 1, false));
        inv.setItem(6, glass("+10", 10, false));
        inv.setItem(7, glass("Set to 64", 64, false));

        inv.setItem(0, getCompass(item));
        inv.setItem(8, ShopHelper.shopSkull(p));
    }

    private static ItemStack getShopItem(ItemStack item, int amount, Player p) {
        ItemStack newItem = item.clone();
        double buy = NBT.getDouble(item, "buy") * amount;
        double sell = NBT.getDouble(item, "sell") * amount;
        double sellAll = NBT.getDouble(item, "sell") * getPlayerAmount(item.getType(), p);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§f- §7Buy§f: §f$§a" + ShopHelper.formatPrice(buy) + " §8§o(Left Click)");
        if (sell != 0) {
            lore.add("§f- §7Sell§f: §f$§c" + ShopHelper.formatPrice(sell) + " §8§o(Right Click)");
            lore.add("§f- §7Sell All§f: §f$§c" + ShopHelper.formatPrice(sellAll) + " §8§o(Middle Click)");
        }
        ItemMeta meta = newItem.getItemMeta();
        meta.setLore(lore);
        newItem.setItemMeta(meta);
        newItem.setAmount(amount);
        return newItem;
    }
    private static int getPlayerAmount(Material material, Player p) {
        ItemStack[] items = p.getInventory().getContents();
        int amount = 0;

        for (ItemStack item : items) {
            if (item != null && item.getType() == material && !item.hasItemMeta()) {
                amount = amount + item.getAmount();
            }
        }

        return amount;
    }
    private static ItemStack getCompass(ItemStack item) {
        String category = NBT.getString(item, "category");
        int page = NBT.getInt(item, "page");

        NBTTagCompound tag = NBT.getCompound(new ItemStack(Material.COMPASS));
        NBT.addTag(tag, "category", category);
        NBT.addTag(tag, "page", page);

        ItemStack compass = NBT.getItem(tag, new ItemStack(Material.COMPASS));
        ItemMeta meta = compass.getItemMeta();
        meta.setDisplayName("§cGo Back");

        compass.setItemMeta(meta);
        return compass;
    }

    private static ItemStack glass(String name, int amount, boolean isRed) {
        Material material = Material.GREEN_STAINED_GLASS_PANE;
        String nameColor = "§a";
        if (isRed) {
            material = Material.RED_STAINED_GLASS_PANE;
            nameColor = "§c";
        }
        String displayName = (nameColor + name);
        int count = amount;
        if (amount == 0) {
            count = 1;
        }
        ItemStack glass = new ItemStack(material, count);
        glass = NBT.addTag(glass, "count", amount);
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(nameColor + displayName);

        glass.setItemMeta(meta);

        return glass;
    }
    private static void minusGlass(Inventory inv, Player p, int count) {
        ItemStack item = inv.getItem(4);
        if (item != null) {
            if (count == 0) {
                inv.setItem(4, getShopItem(item, 1, p));
            }
            else {
                int amount = item.getAmount() - count;
                if (amount < 1) {
                    amount = 1;
                }
                inv.setItem(4, getShopItem(item, amount, p));
            }
        }
    }
    private static void plusGlass(Inventory inv, Player p, int count) {
        ItemStack item = inv.getItem(4);
        if (item != null) {
            int amount = item.getAmount() + count;
            if (amount > 64) {
                amount = 64;
            }
            inv.setItem(4, getShopItem(item, amount, p));
        }
    }


}
