package me.giveblock.gbshop;

import me.giveblock.gbshop.api.listeners.ShopClick;
import me.giveblock.gbshop.commands.Shop;
import me.giveblock.gbshop.commands.Test;
import me.giveblock.gbshop.utils.FileSystem;
import org.bukkit.plugin.java.JavaPlugin;

public final class GBShop extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        FileSystem.init();

        registerCommands();
        registerEvents();

        getLogger().info("GBShop Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("GBShop Disabled");
    }

    private void registerCommands() {
        this.getCommand("shop").setExecutor(new Shop(this));
        this.getCommand("test").setExecutor(new Test(this));

    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new ShopClick(), this);
    }


}
