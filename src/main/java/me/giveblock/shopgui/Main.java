package me.giveblock.shopgui;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("ShopGUI Enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
