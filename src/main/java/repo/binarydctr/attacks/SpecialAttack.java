package repo.binarydctr.attacks;

import repo.binarydctr.attacks.activator.Activator;
import repo.binarydctr.attacks.activator.ActivatorCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * The Main Special Attack class <br>
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public abstract class SpecialAttack implements ActivatorCallback {

    private final Activator activator;

    /**
     * @param activator The activator to use
     */
    public SpecialAttack(Activator activator) {
        this.activator = activator;
    }

    /**
     * @return The used Activator
     */
    public Activator getActivator() {
        return activator;
    }

    /**
     * Parses a Special Attack from a String <br>
     * <strong>NOTE: </strong>Throws {@link IllegalArgumentException} if something went wrong while parsing
     *
     * @param string The String to parse
     * @return The parsed Attack
     */
    public static SpecialAttack parse(String string) {

        // Check if input is null or empty
        if (string == null || string.isEmpty())
            throw new IllegalArgumentException("The string can not be null or empty");

        // Split properties
        String[] prop = string.split(";");
        String name = prop[0]; // Resolve name


        Map<String, String> properties = new HashMap<>();

        // Transferring properties array into mapped state
        for (int i = 1; i < prop.length; i++) {
            String[] s = prop[i].split(":");
            properties.put(s[0], s[1]);
        }

        // Check if the "on" property exists
        if (!properties.containsKey("on"))
            throw new IllegalArgumentException("Every Special Attack needs an activator ('on' property)");


        // Parsing the activator
        Activator activator = Activator.parse(properties.get("on"));

        SpecialAttack attack;

        try {
            // Parsing the attacks
            switch (name) {
                case "knock_player": // Parsing the Knock Player Attack
                    checkForProperties(properties, "radius", "damage", "velocity");

                    attack = new KnockPlayersAttack(activator,
                            Double.parseDouble(properties.get("radius")),
                            Integer.parseInt(properties.get("damage")),
                            Double.parseDouble(properties.get("velocity")));
                    break;
                case "strength": // Parsing the Strength Attack
                    checkForProperties(properties, "duration", "amplifier");

                    attack = new StrengthAttack(activator,
                            Integer.parseInt(properties.get("duration")),
                            Integer.parseInt(properties.get("amplifier")));
                    break;
                case "leap": // Parsing the Leap Attack
                    checkForProperties(properties, "velocity");

                    attack = new LeapAttack(activator,
                            Double.parseDouble(properties.get("velocity")));
                    break;

                // ===================
                // Parsing More Attacks
                // ====================
                default:
                    throw new IllegalArgumentException("Special Attack '" + name + "' could not be found");
            }

            // Set the activator callback
            attack.getActivator().setCallback(attack);

            return attack;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static void checkForProperties(Map<String, String> map, String... props) {

        for (String prop : props) {
            if (!map.containsKey(prop))
                throw new IllegalArgumentException("Property '" + prop + "' is missing");
        }

    }
}
