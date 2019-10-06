package repo.binarydctr.kits;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import repo.binarydctr.ItemParser;
import repo.binarydctr.KitPvP;
import repo.binarydctr.attacks.SpecialAttack;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

/**
 * The Kit Configuration is reading and parsing the kits.yml configuration file <br>
 * Created by Florian Hergenhahn at 2019-10-06 <br>
 *
 * @author Florian Hergenhahn
 */
public class KitConfiguration {

    private final KitPvP plugin;
    private final File file;
    private final YamlConfiguration configuration;

    /**
     * @param file   The location of the configuration file to read
     * @param plugin The plugin instance
     */
    public KitConfiguration(File file, KitPvP plugin) {
        this.plugin = plugin;
        this.file = file;

        // Initializing the Bukkit YamlConfiguration parser
        this.configuration = YamlConfiguration.loadConfiguration(file);
        load();
    }

    /**
     * Loads and initializes the configuration
     */
    private void load() {
        try {
            this.configuration.load(file); // Loading / Parsing the configuration file
        } catch (IOException | InvalidConfigurationException e) {
            plugin.getLogger().log(Level.INFO, "[ERROR] Could not read the kits.yml configuration file");
            e.printStackTrace();
        }
    }

    public Set<Kit> getKits() {
        Set<Kit> kits = new HashSet<>();
        ConfigurationSection configSection = configuration.getConfigurationSection("kits");

        for (String key : configSection.getKeys(false)) {
            try {
                String name = key;

                String permission = configSection.getString(key + ".permission");


                // Parsing Icon of Kit
                ItemStack icon = ItemParser.parse(configSection.getString(key + ".icon"));

                List<ItemStack> items = new ArrayList<>();
                Collection<SpecialAttack> attacks = new HashSet<>();


                // Parsing all items in kit
                for (String item : configSection.getStringList(key + ".items")) items.add(ItemParser.parse(item));



            } catch (IllegalArgumentException e) {
                plugin.getLogger().log(Level.INFO, "[ERROR] Something went wrong while parsing kit '" + key + "'");
                e.printStackTrace();
            }

        }

        return kits;
    }


}
