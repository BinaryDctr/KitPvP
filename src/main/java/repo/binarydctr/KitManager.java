package repo.binarydctr;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import repo.binarydctr.kits.Kit;

import java.util.*;

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

    private ItemStack kitSelector;

    private KitPvP plugin;
    private Set<Kit> kits;
    private Map<UUID, Kit> kitMap;
    private Map<Integer, Kit> kitInventoryMap;

    private static KitManager instance;

    public KitManager(KitPvP plugin) {
        this.plugin = plugin;
        instance = this;
        this.kits = plugin.getKitConfig().getKits();

        kitInventoryMap = new HashMap<>();
        kitMap = new HashMap<>();

        initKitSelector();
    }

    /**
     * Initializes the Global Kit Selector
     */
    private void initKitSelector() {
        // Init Item
        kitSelector = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta = kitSelector.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + "Kit Selector");
        itemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Right-Click to open menu."));
        kitSelector.setItemMeta(itemMeta);

        // Init Kit Inventory Map
        int i = 0;
        for (Kit kit : kits) {
            kitInventoryMap.put(i, kit);
            i++;
        }
    }

    /**
     * Prepares the Kit Inventory for the player
     *
     * @param player The player to use
     * @return The prepared Inventory
     */
    public Inventory prepareKitInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, "Kits");

        for (Map.Entry<Integer, Kit> entry : kitInventoryMap.entrySet()) {
            Kit kit = entry.getValue();
            if (player.hasPermission(kit.getPermission()) || player.isOp()) {
                inventory.setItem(entry.getKey(), kit.getIcon());
                continue;
            }

            ItemStack itemStack = new ItemStack(Material.REDSTONE);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.RED + "UNKNOWN KIT");
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(entry.getKey(), itemStack);
        }

        return inventory;
    }

    public ItemStack getKitSelector() {
        return this.kitSelector;
    }

    public Kit getPlayerKit(Player player) {
        return kitMap.getOrDefault(player.getUniqueId(), null);
    }

    public static KitManager getInstance() {
        return instance;
    }

    public Map<Integer, Kit> getKitInventoryMap() {
        return kitInventoryMap;
    }

    public Map<UUID, Kit> getKitMap() {
        return kitMap;
    }

    public Set<Kit> getKits() {
        return kits;
    }
}
