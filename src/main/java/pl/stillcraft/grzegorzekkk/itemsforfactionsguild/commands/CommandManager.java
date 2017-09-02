package pl.stillcraft.grzegorzekkk.itemsforfactionsguild.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.stillcraft.grzegorzekkk.itemsforfactionsguild.utils.ConfigStorage;

import java.util.HashMap;
import java.util.List;

public class CommandManager implements CommandExecutor {
    public static final String PERM_BEGIN = "iffg";
    private static HashMap<List<String>, SubCMD> commands = new HashMap<>();

    public static void addComand(List<String> cmds, SubCMD s) {
        commands.put(cmds, s);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length >= 1) {
            boolean match = false;

            for (List<String> s : commands.keySet()) {
                if (s.contains(args[0])) {
                    commands.get(s).runCommand(sender, cmd, label, args);
                    match = true;
                }
            }

            if (!match) {
                sender.sendMessage(ConfigStorage.getMsgFormat("This command does not exist!"));
            }
        } else {
            sender.sendMessage(new String[]{
                    ConfigStorage.getMsgFormat("ItemsForFactionsGuild help page."),
                    ConfigStorage.getMsgFormat("Plugin created by grzegorzekkk."),
                    ConfigStorage.getMsgFormat("Available commands: "),
                    ConfigStorage.getMsgFormat("&9/iffg reload&r - reloads data from configuration file")
            });
        }

        return true;
    }
}
