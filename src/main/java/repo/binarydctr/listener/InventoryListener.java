package repo.binarydctr.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import repo.binarydctr.KitManager;
import repo.binarydctr.KitPvP;
import repo.binarydctr.kits.Kit;

import java.util.Map;

/**
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public class InventoryListener implements Listener {

    private final KitPvP plugin;

    public InventoryListener(KitPvP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if (player.getInventory().getItemInMainHand() != null &&
                player.getInventory().getItemInMainHand().isSimilar(KitManager.getInstance().getKitSelector())) {
            player.openInventory(KitManager.getInstance().prepareKitInventory(player));
            return;
        }

        if (player.getInventory().getItemInOffHand() != null &&
                player.getInventory().getItemInOffHand().isSimilar(KitManager.getInstance().getKitSelector()))
            player.openInventory(KitManager.getInstance().prepareKitInventory(player));
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().contains("Kits")) {
            event.setCancelled(true);

            if (event.getRawSlot() < 0) return;

            Map<Integer, Kit> kitMap = KitManager.getInstance().getKitInventoryMap();

            if (!kitMap.containsKey(event.getRawSlot())) return;

            Kit kit = kitMap.get(event.getRawSlot());
            if (!event.getWhoClicked().hasPermission(kit.getPermission()) && !event.getWhoClicked().isOp()) {
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().sendMessage("This kit seems to be unknown.");
                return;
            }

            if (event.isRightClick()) {
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().openInventory(kit.openPreview((Player) event.getWhoClicked()));
                return;
            }

            if (event.isLeftClick()) {
                event.getWhoClicked().closeInventory();
                kit.apply((Player) event.getWhoClicked());
            }

            return;
        }

        if (event.getInventory().getTitle().contains("Kit Preview")) event.setCancelled(true);
    }
}
