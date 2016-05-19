package repo.binarydctr.kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
public class Knight extends Kit {

    public Knight() {
        super("Knight", "kitpvp.knight");
    }

    @Override
    public ItemStack displayItem() {
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + getName() + " Kit");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "IRON SWORD x1");
        lore.add(ChatColor.GRAY + "IRON HELMET x1");
        lore.add(ChatColor.GRAY + "DIAMOND CHESTPLATE x1");
        lore.add(ChatColor.GRAY + "IRON LEGGINGS x1");
        lore.add(ChatColor.GRAY + "IRON BOOTS x1");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public Inventory kitPreview() {
        Inventory inventory = Bukkit.createInventory(null, 9, getName() + " Kit Preview");

        inventory.addItem(new ItemStack(Material.IRON_SWORD));
        inventory.addItem(new ItemStack(Material.IRON_HELMET));
        inventory.addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
        inventory.addItem(new ItemStack(Material.IRON_LEGGINGS));
        inventory.addItem(new ItemStack(Material.IRON_BOOTS));

        return inventory;
    }

    @Override
    public void apply(Player player) {
        player.getInventory().clear();
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
        this.getPlayers().add(player);
        System.out.println("ADDED INTO ARRAYLIST");
    }

    /*
    GROUND POUND
     */

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (!this.getPlayers().contains(player)) {
            return;
        }

        if (action == Action.RIGHT_CLICK_AIR) {
            if (player.getItemInHand().getType() == Material.IRON_SWORD) {
                for (Entity entity : player.getNearbyEntities(5.0, 5.0, 5.0)) {
                    if (entity instanceof Player) {
                        entity.setVelocity(((Player) entity).getEyeLocation().getDirection().multiply(-2.0));
                        ((Player) entity).damage(5);
                    }
                }
                player.sendMessage(ChatColor.BLUE + "You used ground pound.");
            }
        }
    }
}
