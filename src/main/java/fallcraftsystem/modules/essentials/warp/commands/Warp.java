package fallcraftsystem.modules.essentials.warp.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.warp.utils.STATIC;
import fallcraftsystem.modules.essentials.warp.utils.WarpFile;
import fallcraftsystem.utils.MethodsStatics;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {
    public FallCraftSystem plugin;
    private FileConfiguration locationFile;

    public Warp(final FallCraftSystem plugin) {
        this.locationFile = WarpFile.getWarpFile();
        this.plugin = plugin;
        plugin.getCommand("warp").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Commands for only players!");
            return true;
        }
        final Player player = (Player) sender;
        if (args.length == 0) {
            Warps.openInv(player, this.locationFile);

            return true;
        }
        args[0] = args[0].toUpperCase();
        if (!this.locationFile.contains("warps." + args[0])) {
            sender.sendMessage(MethodsStatics.formater(STATIC.PREFIX + "&c&lThis warp not exists!"));
            return true;
        }
        final Double X = Double.parseDouble(this.locationFile.getString("warps." + args[0] + ".X"));
        final Double Y = Double.parseDouble(this.locationFile.getString("warps." + args[0] + ".Y"));
        final Double Z = Double.parseDouble(this.locationFile.getString("warps." + args[0] + ".Z"));
        final String world = this.locationFile.getString("warps." + args[0] + ".WOLRD");
        final String permission = this.locationFile.getString("warps." + args[0] + ".PERMISSION");
        if (!player.hasPermission(permission)) {
            sender.sendMessage(MethodsStatics.formater(STATIC.PREFIX + "&c&lYou don`t have permission for this warp!"));
            return true;
        }
        final Location loc = new Location(Bukkit.getWorld(world), X, Y, Z);
        sender.sendMessage(MethodsStatics.formater(STATIC.PREFIX + "&c&lTeleported to: &6" + args[0].toUpperCase()));
        player.teleport(loc);
        player.playSound(loc, Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
        return true;
    }
}
