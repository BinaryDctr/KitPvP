package repo.binarydctr.kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class Warrior extends Kit {

    public Warrior() {
        super("Warrior", "kitpvp.warrior");
    }

    @Override
    public ItemStack displayItem() {
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + getName() + " Kit");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "DIAMOND AXE x1");
        lore.add(ChatColor.GRAY + "IRON HELMET x1");
        lore.add(ChatColor.GRAY + "IRON CHESTPLATE x1");
        lore.add(ChatColor.GRAY + "IRON LEGGINGS x1");
        lore.add(ChatColor.GRAY + "IRON BOOTS x1");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public Inventory kitPreview() {
        Inventory inventory = Bukkit.createInventory(null, 9, getName() + " Kit Preview");

        inventory.addItem(new ItemStack(Material.DIAMOND_AXE));
        inventory.addItem(new ItemStack(Material.IRON_HELMET));
        inventory.addItem(new ItemStack(Material.IRON_CHESTPLATE));
        inventory.addItem(new ItemStack(Material.IRON_LEGGINGS));
        inventory.addItem(new ItemStack(Material.IRON_BOOTS));
        inventory.addItem(new ItemStack(Material.BOW));
        inventory.addItem(new ItemStack(Material.ARROW, 16));

        return inventory;
    }

    @Override
    public void apply(Player player) {
        this.getPlayers().add(player);
        player.getInventory().clear();
        player.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE));
        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
        player.getInventory().addItem(new ItemStack(Material.BOW));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 16));
    }

    /*
    BERSERK
     */

    @EventHandler
    public void onShift(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();

        if (!this.getPlayers().contains(player)) {
            return;
        }

        if (event.isSneaking()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3, 200));
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 3, 200));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3, 200));
        }
    }
}
