package repo.binarydctr;

import org.bukkit.plugin.java.JavaPlugin;
import repo.binarydctr.entity.EntityHandler;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class KitPvP extends JavaPlugin {


    /*
    5/19/16 added to Github Repo.
     */

    @Override
    public void onEnable() {
        new KitManager(this);
        EntityHandler entityHandler = new EntityHandler(this);
        entityHandler.createEntities();
    }

    @Override
    public void onDisable() {

    }
}
