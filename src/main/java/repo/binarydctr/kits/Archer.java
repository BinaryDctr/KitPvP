package repo.binarydctr.kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
public class Archer extends Kit {

    public Archer() {
        super("Archer", "kitpvp.archer");
    }

    @Override
    public ItemStack displayItem() {
        ItemStack itemStack = new ItemStack(Material.BOW);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + getName() + " Kit");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "STONE SWORD x1");
        lore.add(ChatColor.GRAY + "BOW x1");
        lore.add(ChatColor.GRAY + "ARROWS x64");
        lore.add(ChatColor.GRAY + "LEATHER HELMET x1");
        lore.add(ChatColor.GRAY + "LEATHER CHESTPLATE x1");
        lore.add(ChatColor.GRAY + "LEATHER LEGGINGS x1");
        lore.add(ChatColor.GRAY + "LEATHER BOOTS x1");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public Inventory kitPreview() {
        Inventory inventory = Bukkit.createInventory(null, 9, getName() + " Kit Preview");

        inventory.addItem(new ItemStack(Material.STONE_SWORD));
        inventory.addItem(new ItemStack(Material.BOW));
        inventory.addItem(new ItemStack(Material.ARROW, 64));
        inventory.addItem(new ItemStack(Material.LEATHER_HELMET));
        inventory.addItem(new ItemStack(Material.LEATHER_CHESTPLATE));
        inventory.addItem(new ItemStack(Material.LEATHER_LEGGINGS));
        inventory.addItem(new ItemStack(Material.LEATHER_BOOTS));

        return inventory;
    }

    @Override
    public void apply(Player player) {
        this.getPlayers().add(player);
        player.getInventory().clear();
        player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
        player.getInventory().addItem(new ItemStack(Material.BOW));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 64));
        player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
    }

    /*
    LEAP
     */

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (!this.getPlayers().contains(player)) {
            return;
        }

        if (action == Action.RIGHT_CLICK_AIR) {
            if (player.getItemInHand().getType() == Material.STONE_SWORD) {
                player.setVelocity(player.getEyeLocation().getDirection().multiply(2.0));
                player.sendMessage(ChatColor.BLUE + "You used leap.");
            }
        }
    }
}
