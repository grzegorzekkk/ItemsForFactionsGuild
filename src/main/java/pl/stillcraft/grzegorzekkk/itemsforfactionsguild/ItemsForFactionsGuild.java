package pl.stillcraft.grzegorzekkk.itemsforfactionsguild;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.commands.CommandManager;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.commands.ReloadCMD;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.listeners.FactionCreateListener;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.utils.ConfigStorage;

import java.util.Arrays;
import java.util.logging.Logger;

public class ItemsForFactionsGuild extends JavaPlugin {

    public static Logger log;
    public static JavaPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        log = getLogger();

        ConfigStorage.createConfig();
        ConfigStorage.loadConfig();

        registerListeners();
        registerCommands();

        log.info("Enabled ItemsForFactionsGuild!");
        log.info("Plugin created by grzegorzekkk");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        instance = null;

        log.info("Disabled ItemsForFactionsGuild!");
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new FactionCreateListener(), this);
    }

    private void registerCommands() {
        getCommand("iffg").setExecutor(new CommandManager());

        CommandManager.addComand(Arrays.asList("reload", "r"), new ReloadCMD());
    }
}
