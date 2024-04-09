package me.giveblock.gbshop.api.listeners;

import me.giveblock.gbshop.api.gui.ShopHome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class ShopClick implements Listener {

    private static boolean isItem(InventoryClickEvent e) {
        Inventory inv = e.getClickedInventory();
        int slot = e.getSlot();
        if (inv != null) {
            return (!e.getSlotType().equals(InventoryType.SlotType.OUTSIDE) && inv.getItem(slot) != null);
        }
        return false;
    }

    @EventHandler
    public void ShopHomeClick(InventoryClickEvent e) {
        String title = e.getView().getTitle();
        if (title.contains("ShopGUI")) {
            e.setCancelled(true);
            if (title.endsWith("Home") && isItem(e)) {
                ShopHome.onClick(e);
            }
        }


    }


}
