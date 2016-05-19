package repo.binarydctr.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public abstract class Kit implements Listener {

    private String name;
    private String permission;
    private ArrayList<Player> players = new ArrayList<Player>();

    public Kit(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }

    public abstract ItemStack displayItem();

    public abstract Inventory kitPreview();

    public abstract void apply(Player player);

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /*
    MAKES IT EASY FOR THE KITS TO HAVE SPECIAL POWERS
     */
}