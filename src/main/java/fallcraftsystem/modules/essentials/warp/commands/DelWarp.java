package fallcraftsystem.modules.essentials.warp.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.warp.utils.STATIC;
import fallcraftsystem.modules.essentials.warp.utils.WarpFile;
import fallcraftsystem.utils.MethodsStatics;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DelWarp implements CommandExecutor {
    public FallCraftSystem plugin;
    private FileConfiguration locationFile;

    public DelWarp(final FallCraftSystem plugin) {
        this.locationFile = WarpFile.getWarpFile();
        this.plugin = plugin;
        plugin.getCommand("delwarp").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Commands for only players!");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(MethodsStatics.formater(String.valueOf(STATIC.PREFIX) + "&c&lThis warp not exists!"));
            return true;
        }
        this.locationFile.set("warps." + args[0].toUpperCase(), null);
        WarpFile.save();
        sender.sendMessage(MethodsStatics.formater(String.valueOf(STATIC.PREFIX) + "&c&lWarp deleted!"));
        return true;
    }
}
