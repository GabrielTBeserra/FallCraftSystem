// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.commands.admin;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
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
            Bukkit.broadcast(Ultilities.formater(
                    ServerUtils.SERVER_NAME + "&eO player &c" + PlayerSender.getName() + " &eteleportou para o player &2" + player1.getName())
                    , "fallcraft.module.essentials.tp");
        } else if (args.length == 1) {
            final Player player1 = Bukkit.getPlayer(args[0]);
            if (!player1.isOnline() || player1 == null) {
                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player nao esta online!"));
                return true;
            }
            PlayerSender.teleport(player1.getLocation());


            Bukkit.broadcast(Ultilities.formater(
                    ServerUtils.SERVER_NAME + "&eO player &c" + PlayerSender.getName() + " &eteleportou para o player &2" + player1.getName())
                    , "fallcraft.module.essentials.tp");

        } else {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&2&l/tp <Player1> <Player2>!"));
        }
        return true;
    }
}
