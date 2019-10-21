package net.euphalys.euphawarp.commands;

import net.euphalys.euphawarp.EuphaWarp;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Dinnerwolph
 */
public class WarpCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length == 0) {
                player.sendMessage("/warp list : list des warps");
                player.sendMessage("/warp set <warpName> : créer un warp");
                player.sendMessage("/warp <warpName> : tp à un warp");
            } else if (args[0].equalsIgnoreCase("list")) {
                player.sendMessage("Warp list :");
                for (String warp : EuphaWarp.warp.keySet()) {
                    player.sendMessage(warp);
                }
            } else if (args[0].equalsIgnoreCase("set")) {
                if (args[1] == null) {
                    player.sendMessage("/warp set <warpName>");
                } else {
                    EuphaWarp.warpManager.registerWarps(args[1], player.getLocation());
                }
            } else {
                Location location = EuphaWarp.warp.get(args[0]);
                if (location == null) {
                    player.sendMessage("Ce warp n'éxiste pas :(");
                } else {
                    player.teleport(location);
                }
            }
        }
        return true;
    }
}
