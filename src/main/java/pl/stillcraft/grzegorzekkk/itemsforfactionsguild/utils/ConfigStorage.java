package pl.stillcraft.grzegorzekkk.itemsforfactionsguild.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.ItemsForFactionsGuild;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Container class used to store Configuration entries of plugin.
 */
public class ConfigStorage {

    public static final String CHAT_PREFIX = "[&cIFFG&r] ";
    private static FileConfiguration config;
    private static List<ItemStack> items;
    private static String msg;

    private ConfigStorage() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Loads config file to plugin memory
     *
     * @return true if loaded successfully
     */
    public static boolean loadConfig() {

        ItemsForFactionsGuild.instance.reloadConfig();
        config = ItemsForFactionsGuild.instance.getConfig();

        config.addDefault("not_enough_msg", "&cNot enough items to create a Faction. Get more: ");
        String[] exampleItems = {"WOOD, 5", "DIAMOND, 10", "OBSIDIAN, 20"};
        config.addDefault("required.items", Arrays.asList(exampleItems));
        config.options().copyDefaults(true);

        loadItems();
        loadMessages();

        return true;
    }

    /**
     * Creates default config file
     */
    public static void createConfig() {
        ItemsForFactionsGuild.instance.saveDefaultConfig();
    }

    private static boolean loadItems() {
        items = new LinkedList<>();

        List<String> rawItems = config.getStringList("required.items");

        ConsoleLogger.info(ChatColor.YELLOW + "Found items required to create guild:");

        // Loop through all items found in configuration
        for (String rawItem : rawItems) {

            String[] splitItemstack = rawItem.split(", ");
            String[] splitItem = splitItemstack[0].split(":");

            Material material = Material.matchMaterial(splitItem[0]);
            if (material == null) continue;

            int materialAmount = Integer.parseInt(splitItemstack[1]);

            ItemStack i = new ItemStack(material, materialAmount);

            if (splitItem.length > 1) {
                Byte itemData = Byte.parseByte(splitItem[1]);
                i = new ItemStack(material, materialAmount, itemData);
            }

            items.add(i);
            ConsoleLogger.info(ChatColor.YELLOW + i.getType().name() + ", " + i.getAmount());
        }

        return (items.isEmpty()) ? false : true;
    }

    private static boolean loadMessages() {
        msg = config.getString("not_enough_msg");
        return true;
    }

    /**
     * Returns String with plugin chat prefix and formatted colors
     *
     * @param msg message to format
     * @return formatted string
     */
    public static String getMsgFormat(String msg) {
        return ChatColor.translateAlternateColorCodes('&', CHAT_PREFIX + msg);
    }


    public static List<ItemStack> getRequiredItems() {
        return items;
    }

    public static String getInsufficientRsrcsMsg() {
        return msg;
    }

}
