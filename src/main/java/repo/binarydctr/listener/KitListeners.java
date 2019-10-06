package repo.binarydctr.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import repo.binarydctr.KitManager;
import repo.binarydctr.KitPvP;
import repo.binarydctr.attacks.SpecialAttack;
import repo.binarydctr.kits.Kit;

/**
 * Created by Florian Hergenhahn at 2019-10-07 <br>
 *
 * @author Florian Hergenhahn
 */
public class KitListeners implements Listener {

    public KitListeners(KitPvP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    private void onShift(PlayerToggleSneakEvent event) {

        Kit kit = KitManager.getInstance().getPlayerKit(event.getPlayer());
        if(kit == null) return;

        for (SpecialAttack specialAttack : kit.getSpecialAttacks()) {
            if(specialAttack.getActivator().isEventApplicable(event.getClass()))
                specialAttack.getActivator().tryActivate(event);
        }
    }

    @EventHandler
    private void onInteract(PlayerInteractEvent event) {
        Kit kit = KitManager.getInstance().getPlayerKit(event.getPlayer());
        if(kit == null) return;

        for (SpecialAttack specialAttack : kit.getSpecialAttacks()) {
            if(specialAttack.getActivator().isEventApplicable(event.getClass()))
                specialAttack.getActivator().tryActivate(event);
        }
    }

}
