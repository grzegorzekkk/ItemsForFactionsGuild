package pl.stillcraft.grzegorzekkk.itemsforfactionsguild;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.commands.CommandManager;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.commands.ReloadCMD;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.listeners.FactionCreateListener;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.utils.ConfigStorage;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.utils.ConsoleLogger;

import java.util.Arrays;

public class ItemsForFactionsGuild extends JavaPlugin {

    private static JavaPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        ConsoleLogger.setLogger(getLogger());

        ConfigStorage.createConfig();
        ConfigStorage.loadConfig();

        registerListeners();
        registerCommands();

        ConsoleLogger.info("Enabled ItemsForFactionsGuild!");
        ConsoleLogger.info("Plugin created by grzegorzekkk");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        instance = null;

        ConsoleLogger.info("Disabled ItemsForFactionsGuild!");
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new FactionCreateListener(), this);
    }

    private void registerCommands() {
        getCommand("iffg").setExecutor(new CommandManager());

        CommandManager.addComand(Arrays.asList("reload", "r"), new ReloadCMD());
    }

    public static JavaPlugin getIffgInstance() {
        return instance;
    }

}
