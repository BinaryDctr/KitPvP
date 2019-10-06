package repo.binarydctr.attacks.activator;

import org.bukkit.event.Event;
import repo.binarydctr.ItemParser;

/**
 * The Activator Class can be used to trigger certain things when one or multiple events fire <br>
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public abstract class Activator {

    private final Class<? extends Event>[] applicableEvents;
    private ActivatorCallback callback;

    /**
     * @param applicableEvents An Array of classes which the Activator can use to activate
     */
    public Activator(Class<? extends Event>[] applicableEvents) {
        this.applicableEvents = applicableEvents;
    }

    /**
     * @param callback The callback to set
     */
    public void setCallback(ActivatorCallback callback) {
        this.callback = callback;
    }

    /**
     * Checks if the necessary states are satisfied that the Activator can be Activated
     *
     * @param event The event to check
     * @return True if the event can be activated
     */
    public abstract boolean canActivate(Event event);

    /**
     * Checks if the Event is Applicable for activation
     *
     * @param event The event class to check
     * @return True if the given Event Class is applicable to activate or false otherwise
     */
    public boolean isEventApplicable(Class<? extends Event> event) {
        for (Class<? extends Event> applicableEvent : applicableEvents) {
            if (event == applicableEvent) return true;
        }

        return false;
    }

    /**
     * This method tries to activate the Activator <br>
     * <strong>IMPORTANT: </strong>The method does not check if the event is applicable or not,
     * see {@link #isEventApplicable(Class)}
     *
     * @param event The event to activate the Activator
     */
    public void tryActivate(Event event) {
        if (canActivate(event))
            this.callback.activate(this, event);
    }


    /**
     * Parses a String to an Activator <br>
     * <strong>IMPORTANT: </strong>The activators have no callback when parsed. <br>
     * If the input is invalid it is going to throw an {@link IllegalArgumentException}
     *
     * @param string   The string to parse
     * @return The parsed Activator
     */
    public static Activator parse(String string) {

        // Checking if the input is null or empty
        if (string == null || string.isEmpty())
            throw new IllegalArgumentException("The string can not be null or empty");

        String name;
        String[] args = new String[]{};

        // Check if activator has arguments
        if (string.contains(",")) {
            args = string.split(","); // Resolve arguments
            name = args[0];
        } else
            name = string;

        // Parsing the different activators
        switch (name) {
            case "item": // Parsing the Item Activator
                if (args.length < 2) // Check if there are enough provided arguments
                    throw new IllegalArgumentException("The Item Activator needs at least 1 argument");

                // Create new Item Activator
                return new ItemActivator(ItemParser.parse(args[1]));

            case "shift": // Parsing the Shift Activator
                return new ShiftActivator(); // Creating the Shift Activator

            // =================================
            // Parsing More Activators In Future
            // =================================
        }

        throw new IllegalArgumentException("The activator '" + name + "' could not be found");
    }
}
