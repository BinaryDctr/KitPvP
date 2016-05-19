package repo.binarydctr.entity;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import repo.binarydctr.kits.KitType;

import java.util.HashMap;

/**
 * ******************************************************************
 * Copyright BinaryDctr (c) 2016. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of BinaryDctr. Distribution, reproduction, taking snippets, or
 * claiming any contents as your will break the terms of the license, and void any
 * agreements with you, the third party.
 * ******************************************************************
 **/
public class EntityHandler implements Listener {

    public JavaPlugin plugin;

    public HashMap<Entity, KitEntity> entityMap = new HashMap<>();

    public EntityHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void createEntities() {
        for(KitType types : KitType.values()) {
            KitEntity kitEntity = new KitEntity(types.name(), types.kit, types.getType());
            for(World world : Bukkit.getWorlds()) {
                for(int x = 1; x < KitType.values().length; x++) {
                    for(int z = 3; z < KitType.values().length; z--) {
                        Entity entity = world.spawnEntity(world.getSpawnLocation().add(x, 0, z), kitEntity.getType());
                        entityMap.put(entity, kitEntity);
                        //CHUNKS SHOULD BE LOADED CONSIDERING ITS THE WORLD'S SPAWN.
                    }
                }
            }
        }
    } /** I HAVE NO IDEA IF THIS IS GONNA WORK I WILL TEST NEXT TIME **/

    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if(!entityMap.containsKey(entity)) {
            return;
        }

        KitEntity kitEntity = entityMap.get(entity);
        kitEntity.getKit().apply(player);
        //DONE STILL NEED SOME MORE STUFF, LIKE NOAI AND SILENT BUT FOR NOW WE HAVE THE LOGIC
    }

}
