package repo.binarydctr.attacks.activator;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * The Item Activator can be used to activated when a certain Item has been clicked <br>
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public class ItemActivator extends Activator {

    private static final Class[] APPLICABLE_CLASSES = {PlayerInteractEvent.class};
    private final ItemStack item;

    /**
     * @param item     The Item to check for
     */
    public ItemActivator(ItemStack item) {
        super(APPLICABLE_CLASSES);
        this.item = item;
    }


    @Override
    public boolean canActivate(Event event) {

        if (!(event instanceof PlayerInteractEvent)) return false;

        PlayerInteractEvent e = (PlayerInteractEvent) event;

        if (e.getAction() != Action.RIGHT_CLICK_AIR) return false;

        Player player = e.getPlayer();

        if (player.getInventory().getItemInMainHand() != null &&
                player.getInventory().getItemInMainHand().isSimilar(item))
            return true;

        return player.getInventory().getItemInOffHand() != null &&
                player.getInventory().getItemInOffHand().isSimilar(item);
    }
}
