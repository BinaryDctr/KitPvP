package repo.binarydctr.attacks.activator;

import org.bukkit.event.Event;

/**
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public interface ActivatorCallback {


    void activate(Activator activator, Event event);

}
