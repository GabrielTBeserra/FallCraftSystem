// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.commands.admin;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tp implements CommandExecutor {
    public FallCraftSystem plugin;

    public Tp(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("tp").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }
        final Player PlayerSender = (Player) sender;
        if (args.length == 2) {
            final Player player1 = Bukkit.getPlayer(args[0]);
            final Player player2 = Bukkit.getPlayer(args[1]);
            if (!(player1.isOnline() || player2.isOnline()) || (player1 == null || player2 == null)) {
                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player nao esta online!"));
                return true;
            }
            player1.teleport(player2.getLocation());
            for (final Player PPplayer : Bukkit.getOnlinePlayers()) {
                if (PPplayer.hasPermission("fallcraft.module.essentials.tp")) {
                    PPplayer.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6The player &c" + player1.getName() + " &6teleported to &2" + player2.getName()));
                }
            }
        } else if (args.length == 1) {
            final Player player1 = Bukkit.getPlayer(args[0]);
            if (!player1.isOnline() || player1 == null) {
                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player nao esta online!"));
                return true;
            }
            PlayerSender.teleport(player1.getLocation());
            for (final Player PPplayer2 : Bukkit.getOnlinePlayers()) {
                if (PPplayer2.hasPermission("fallcraft.module.essentials.tp")) {
                    PPplayer2.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6The player &c" + PlayerSender.getName() + " &6teleported to &2" + player1.getName()));
                }
            }
        } else {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&2&l/tp <Player1> <Player2>!"));
        }
        return true;
    }
}
