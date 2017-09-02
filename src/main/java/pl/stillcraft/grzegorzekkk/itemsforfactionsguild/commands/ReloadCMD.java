package pl.stillcraft.grzegorzekkk.itemsforfactionsguild.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.utils.ConfigStorage;

/**
 * Reload configuration file command class.
 */
public class ReloadCMD implements SubCMD {

    @Override
    public String getPermission() {
        return CommandManager.PERM_BEGIN + ".reload";
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        ConfigStorage.loadConfig();

        sender.sendMessage(ConfigStorage.getMsgFormat("Configuration has been successfully reloaded."));
    }
}
