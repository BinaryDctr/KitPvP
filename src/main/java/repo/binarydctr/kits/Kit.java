package repo.binarydctr.kits;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import repo.binarydctr.attacks.SpecialAttack;

import java.util.ArrayList;
import java.util.Collection;
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
public class Kit {

    private final String name;
    private final Entity entity;
    private final Collection<SpecialAttack> specialAttacks;
    private final List<ItemStack> items;
    private final String permission;

    /**
     * @param name           The name of the kit
     * @param entity         The entity which relates to the kit, can be null
     * @param specialAttacks All special attacks which can be used by the kit owner
     * @param items          The Kit items
     * @param permission     The permission to access the kit
     */
    public Kit(String name, Entity entity, Collection<SpecialAttack> specialAttacks, List<ItemStack> items,
               String permission) {
        this.name = name;
        this.entity = entity;
        this.specialAttacks = specialAttacks;
        this.items = items;
        this.permission = permission;
    }

    /**
     * @return The name of the kit
     */
    public String getName() {
        return name;
    }

    /**
     * @return The relating entity or null if not existent
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * @return A collection of Special Attacks which the kit owner can use
     */
    public Collection<SpecialAttack> getSpecialAttacks() {
        return specialAttacks;
    }

    /**
     * @return The items the kit contains
     */
    public List<ItemStack> getItems() {
        return items;
    }

    /**
     * @return The kit permissions
     */
    public String getPermission() {
        return permission;
    }
}