package repo.binarydctr.attacks.activator;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public class ShiftActivator extends Activator {

    private static final Class[] APPLICABLE_CLASSES = {PlayerToggleSneakEvent.class};

    public ShiftActivator(ActivatorCallback callback) {
        super(callback, APPLICABLE_CLASSES);
    }

    @Override
    public boolean canActivate(Event event) {

        if(!(event instanceof PlayerToggleSneakEvent)) return false;

        PlayerToggleSneakEvent e = (PlayerToggleSneakEvent) event;

        return e.isSneaking();
    }
}
