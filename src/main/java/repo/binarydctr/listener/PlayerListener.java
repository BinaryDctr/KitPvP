package repo.binarydctr.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import repo.binarydctr.KitManager;
import repo.binarydctr.KitPvP;

/**
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public class PlayerListener implements Listener {

    private final KitPvP plugin;

    public PlayerListener(KitPvP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(0, KitManager.getInstance().getKitSelector());

        player.teleport(player.getWorld().getSpawnLocation());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        player.setHealth(20);
        player.getInventory().clear();
        event.setDroppedExp(0);

        KitManager.getInstance().getKitMap().remove(player.getUniqueId());

        player.teleport(player.getWorld().getSpawnLocation());
        event.setKeepInventory(true);
        player.getInventory().clear();
        player.getInventory().setItem(0, KitManager.getInstance().getKitSelector());

        if (player.getKiller() == null) {
            event.setDeathMessage(player.getName() + " has died from an unknown cause.");
            return;
        }

        Player killer = event.getEntity().getKiller();
        event.setDeathMessage(killer.getName() + " has killed " + player.getName());
    }

}
