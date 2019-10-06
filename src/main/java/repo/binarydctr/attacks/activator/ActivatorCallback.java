package repo.binarydctr.attacks.activator;

import org.bukkit.event.Event;

/**
 * The Activator Callback is calling when certain checks have satisfied <br>
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public interface ActivatorCallback {


    /**
     * Gets called if the Activator has been satisfied
     *
     * @param activator The activator which called the method
     * @param event     The event which activated the Activator
     */
    void activate(Activator activator, Event event);

}
