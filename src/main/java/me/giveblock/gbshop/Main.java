package me.giveblock.gbshop;

import me.giveblock.gbshop.commands.Shop;
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

    private void registerCommands() {
        this.getCommand("shop").setExecutor(new Shop(this));

    }


}
