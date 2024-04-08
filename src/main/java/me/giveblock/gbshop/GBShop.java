package me.giveblock.gbshop;

import me.giveblock.gbshop.commands.Shop;
import org.bukkit.plugin.java.JavaPlugin;

public final class GBShop extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerCommands();

        getLogger().info("GBShop Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("GBShop Disabled");
    }

    private void registerCommands() {
        this.getCommand("shop").setExecutor(new Shop(this));

    }


}
