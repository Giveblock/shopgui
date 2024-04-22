package me.giveblock.gbshop.utils;

import net.minecraft.nbt.*;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBT {

    public static ItemStack getItem(NBTTagCompound compound, ItemStack item) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        nmsItem.c(compound);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }
    public static NBTTagCompound getCompound(ItemStack item) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        return nmsItem.w();
    }

    public static void addTag(NBTTagCompound compound, String tag, Object o) {
        if (o instanceof String) {
            compound.a(tag, NBTTagString.a((String)o));
        }
        if (o instanceof Integer) {
            compound.a(tag, NBTTagInt.a((Integer)o));
        }
        if (o instanceof Double) {
            compound.a(tag, NBTTagDouble.a((Double)o));
        }
        if (o instanceof Float) {
            compound.a(tag, NBTTagFloat.a((Float)o));
        }
        if (o instanceof Long) {
            compound.a(tag, NBTTagLong.a((Long)o));
        }
    }
    public static ItemStack addTag(ItemStack item, String tag, Object o) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.w();
        addTag(compound, tag, o);
        nmsItem.c(compound);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }


    public static int getInt(ItemStack item, String tag) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.w();
        return compound.h(tag);
    }
    public static String getString(ItemStack item, String tag) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.w();
        return compound.l(tag);
    }
    public static Double getDouble(ItemStack item, String tag) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.w();
        return compound.k(tag);
    }



    public static ItemStack setString(ItemStack item, String tag, String value) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.w();
        compound.a(tag, NBTTagString.a(value));
        nmsItem.c(compound);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }
    public static ItemStack setInt(ItemStack item, String tag, int value) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.w();
        compound.a(tag, NBTTagInt.a(value));
        nmsItem.c(compound);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }








}
