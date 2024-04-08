package me.giveblock.gbshop.utils;

import com.google.gson.JsonObject;
import me.giveblock.gbshop.GBShop;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class FileSystem {

    private static final Plugin pl = GBShop.getPlugin(GBShop.class);

    public static JsonObject shop;
    private static final File shopFile = new File(pl.getDataFolder(), "shop.json");

    public static void init() {
        //config file
        pl.saveDefaultConfig();
        //shop file
        pl.saveResource("shop.json", false);
        shop = Json.load(shopFile);

    }

    public static void reload() {
        shop = Json.load(shopFile);
    }

}
