package repo.binarydctr;

import org.bukkit.plugin.java.JavaPlugin;
import repo.binarydctr.entity.EntityHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;

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
        loadConfiguration();
        new KitManager(this);
        EntityHandler entityHandler = new EntityHandler(this);
        entityHandler.createEntities();
    }

    /**
     * This method loads all the configurations
     */
    private void loadConfiguration() {
        if (!getDataFolder().exists())   // Checks if the data folder in the /plugins directory exists
            getDataFolder().mkdir();    // Create data folder

        File kitConfig = new File(getDataFolder(), "kits.yml");
        if (!kitConfig.exists()) {       // Checks if the kits.yml configuration exists
            try {
                // Copies the kits.yml from resources to data folder
                Files.copy(this.getClass().getResourceAsStream("configuration/kits.yml"), kitConfig.toPath());
            } catch (IOException e) {
                getLogger().log(Level.INFO, "[ERROR] Could not copy configuration");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable() {

    }
}
