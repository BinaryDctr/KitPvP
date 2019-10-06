package repo.binarydctr.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import repo.binarydctr.KitManager;
import repo.binarydctr.kits.Kit;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class EntityListener implements Listener {

    private JavaPlugin plugin;

    public EntityListener(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }


    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();


        Kit kit = null;
        for (Kit k : KitManager.getInstance().getKits()) {
            if(entity.equals(k.getEntity())) {
                kit = k;
                break;
            }
        }

        if(kit == null) return;

        kit.apply(player);
        //DONE STILL NEED SOME MORE STUFF, LIKE NOAI AND SILENT BUT FOR NOW WE HAVE THE LOGIC
    }

}
