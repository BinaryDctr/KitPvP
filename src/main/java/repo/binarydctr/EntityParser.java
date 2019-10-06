package repo.binarydctr;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * The Entity Parser class can make an Entity from a String <br>
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public class EntityParser {

    /**
     * Parses / Spawns an Entity from String <br>
     * <strong>NOTE: </strong>Throws {@link IllegalArgumentException} when something went wrong
     *
     * @param entityString The string to parse
     * @return The spawned Entity
     */
    public static Entity parse(String entityString) {

        // Checks if the input is null or empty
        if (entityString == null || entityString.isEmpty())
            throw new IllegalArgumentException("The entity string can not be null or empty");

        // Splits the string into multiple sections with the ';' delimiter
        String[] sections = entityString.split(";");

        // Resolves the Entity Type
        EntityType type = EntityType.valueOf(sections[0].toUpperCase());

        String world = null;
        double x = 0;
        double y = 0;
        double z = 0;

        try {
            for (String section : sections) {
                String[] s = section.split(":");
                String key = s[0];

                // Parsing the properties of the Entity
                switch (key) {
                    case "world":
                        world = s[1];
                        break;
                    case "x":
                        x = Double.parseDouble(s[1]);
                        break;
                    case "y":
                        y = Double.parseDouble(s[1]);
                        break;
                    case "z":
                        z = Double.parseDouble(s[1]);
                        break;
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }

        // Check if the world property exists
        if (world == null)
            throw new IllegalArgumentException("The world property is required to spawn an entity");

        World w = Bukkit.getWorld(world);
        if (w == null) // Checks if the World exists
            throw new IllegalArgumentException("World '" + world + "' could not be found");

        // Spawns the entity and returns the result
        return w.spawnEntity(new Location(w, x, y, z), type);
    }

}
