package repo.binarydctr.attacks.activator;

import org.bukkit.event.Event;

/**
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public abstract class Activator {

    private final ActivatorCallback callback;
    private final Class<? extends Event>[] applicableEvents;

    public Activator(ActivatorCallback callback, Class<? extends Event>[] applicableEvents) {
        this.callback = callback;
        this.applicableEvents = applicableEvents;
    }

    public abstract boolean canActivate(Event event);

    public boolean isEventApplicable(Class<? extends Event> event) {
        for (Class<? extends Event> applicableEvent : applicableEvents) {
            if (event == applicableEvent) return true;
        }

        return false;
    }

    public void tryActivate(Event event) {
        if (canActivate(event))
            this.callback.activate(this, event);
    }
}
