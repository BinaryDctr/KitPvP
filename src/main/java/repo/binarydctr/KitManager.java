package repo.binarydctr;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import repo.binarydctr.kits.*;

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
public class KitManager implements Listener {

    JavaPlugin plugin;

    Kit[] kits;

    static KitManager instance;

    public KitManager(JavaPlugin plugin) {
        this.plugin = plugin;
        instance = this;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        kits = new Kit[]{new Knight(), new Archer(), new Warrior()};
        for (Kit kit : kits) {
            plugin.getServer().getPluginManager().registerEvents(kit, plugin);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().equals(getKitSelector())) {
                player.openInventory(getKitInventory(player));
            }
        }

    }

    Inventory inventory;

    public Inventory getKitInventory(Player player) {
        inventory = Bukkit.createInventory(null, 54, "Kits");
        for (Kit kit : kits) {
            if (player.hasPermission(kit.getPermission()) || player.isOp()) {
                inventory.addItem(kit.displayItem());
            } else {
                ItemStack itemStack = new ItemStack(Material.REDSTONE);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(ChatColor.RED + "UNKNOWN KIT");
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(itemStack);
            }
        }

        return inventory;
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().contains("Kits")) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
                return;
            }

            event.setCancelled(true);

            for (Kit kit : kits) {
                if (event.getCurrentItem().equals(kit.displayItem())) {
                    if (event.isRightClick()) {
                        event.getWhoClicked().closeInventory();
                        event.getWhoClicked().openInventory(kit.kitPreview());
                    } else if (event.isLeftClick()) {
                        event.getWhoClicked().closeInventory();
                        kit.apply((Player) event.getWhoClicked());
                    }
                }
            }

            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("UNKNOWN KIT")) {
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().sendMessage("This kit seems to be unknown.");
            }
        }

        if (event.getInventory().getTitle().contains("Kit Preview")) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
                return;
            }

            event.setCancelled(true);
        }
    }

    public ItemStack getKitSelector() {
        ItemStack itemStack = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + "Kit Selector");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Right-Click to open menu.");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        event.setDeathMessage("");
        player.setHealth(20);
        player.getInventory().clear();
        event.setDroppedExp(0);
        Kit kit = getPlayerKit(player);
        if (kit != null) {
            kit.getPlayers().remove(player);
        }

        if (player.getKiller() == null) {
            player.teleport(player.getWorld().getSpawnLocation());
            event.setKeepInventory(true);
            player.getInventory().clear();
            player.getInventory().setItem(0, getKitSelector());
            event.setDeathMessage(player.getName() + " has died from an unknown cause.");
            return;
        }

        Player killer = event.getEntity().getKiller();

        player.teleport(player.getWorld().getSpawnLocation());
        event.setKeepInventory(true);
        player.getInventory().clear();
        player.getInventory().setItem(0, getKitSelector());

        event.setDeathMessage(killer.getName() + " has killed " + player.getName());
    }

    public Kit getPlayerKit(Player player) {
        for (KitType kitType : KitType.values()) {
            Kit kit = kitType.getKit();
            if (kit.getPlayers().contains(player)) {
                return kit;
            }
        }
        return null;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(0, getKitSelector());

        player.teleport(player.getWorld().getSpawnLocation());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Kit kit = getPlayerKit(player);
        if (kit != null) {
            kit.getPlayers().remove(player);
        }
    }

    public static KitManager getInstance() {
        return instance;
    }

}
