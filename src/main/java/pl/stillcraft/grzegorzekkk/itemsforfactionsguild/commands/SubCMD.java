package pl.stillcraft.grzegorzekkk.itemsforfactionsguild.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.utils.ConfigStorage;

public interface SubCMD {

    String getPermission();

    void onCommand(CommandSender sender, Command cmd, String label, String[] args);

    default boolean needsPlayer() {
        return false;
    }

    default boolean runCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission(getPermission()) && !sender.isOp()) {
            sender.sendMessage(ConfigStorage.getMsgFormat("You do not have permission to perform this command!"));
        } else {
            if (needsPlayer()) {
                if (sender instanceof Player) {
                    onCommand(sender, cmd, label, args);
                }
            } else {
                onCommand(sender, cmd, label, args);
            }
        }

        return true;
    }
}
