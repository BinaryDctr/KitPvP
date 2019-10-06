package repo.binarydctr;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * The Item Parser can be used to Parse a string to an {@link ItemStack} <br>
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public class ItemParser {

    /**
     * Parses a String to an ItemStack <br>
     * <strong>NOTE: </strong>Throws {@link IllegalAccessException} if something went wrong
     *
     * @param itemString The Item Stack String to parse
     * @return The parsed Item Stack
     */
    public static ItemStack parse(String itemString) {

        // Check is the item string is null or empty
        if (itemString == null || itemString.isEmpty())
            throw new IllegalArgumentException("The input can't be null or empty");

        // Split the the string in sections with ';' as delimiter
        String[] sections = itemString.split(";");

        // Retrieving the Material from String
        ItemStack stack = getMaterial(sections[0]);


        for (int i = 1; i < sections.length; i++) {
            // Parsing the Item Stack Properties
            parseProperty(stack, sections[i]);
        }

        return stack;
    }

    /**
     * Parses the property
     *
     * @param itemStack The item stack to parse
     * @param property  The property to parse
     */
    private static void parseProperty(ItemStack itemStack, String property) {
        String[] s = property.split(":"); // Splitting the item properties into key and value
        String key = s[0].toLowerCase();

        try {
            switch (key) {
                case "amount": // Parsing the amount property
                    itemStack.setAmount(Integer.parseInt(s[1]));
                    break;
                // ============================
                // Can add more properties here
                // ============================
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Parses the Material from a string
     *
     * @param mat The material string
     * @return The parsed material
     */
    private static ItemStack getMaterial(String mat) {
        try {
            short data = 0;
            if (mat.contains(":")) {
                String[] d = mat.split(":");
                data = Short.parseShort(d[1]);
                mat = mat.replace(":" + d[1], "");
            }

            Material material;
            if (StringUtils.isNumeric(mat)) material = Material.getMaterial(Integer.parseInt(mat));
            else {
                material = Material.matchMaterial(mat);
            }

            if (material == null)
                throw new IllegalArgumentException("The material '" + mat + "' could not be found");

            return new ItemStack(material, 1, data);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }


}
