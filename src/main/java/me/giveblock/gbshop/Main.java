package me.giveblock.gbshop;

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
        getLogger().info("ShopGUI Disabled");
    }
}
