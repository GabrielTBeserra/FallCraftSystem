// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.FlyStatus;
import fallcraftsystem.utils.PluginInfo;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    public FallCraftSystem plugin;

    public Fly(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("fly").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 1) {
            if (player.hasPermission("fallcraft.module.essentials.fly.admin")) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    player = Bukkit.getPlayer(args[0]);
                    sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&aModo de voo alterado para o player " + player.getName()));
                } else {
                    sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cO player nao esta online"));
                }
            } else {
                sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cYou don`t have permission for this!"));
                return true;
            }

        }


        GamePlayer gm = PluginInfo.players.get(player);

        if (gm.getFlyStatus().equals(FlyStatus.NOT_FLYING)) {
            player.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&aVoo habilitado"));
            player.setAllowFlight(true);
            PluginInfo.players.get(player).setFlyStatus(FlyStatus.FLYING);
        } else {
            player.setAllowFlight(false);
            player.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cVoo desabilitado"));
            PluginInfo.players.get(player).setFlyStatus(FlyStatus.NOT_FLYING);
        }


        return true;
    }
}
