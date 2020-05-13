// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.VanishStatus;
import fallcraftsystem.utils.PluginInfo;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor {
    public FallCraftSystem plugin;

    public Vanish(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("v").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }
        final Player player = (Player) sender;


        GamePlayer gm = PluginInfo.players.get(player);

        if (gm.getVanishStatus().equals(VanishStatus.VISIBLE)) {
            for (final Player playerTarget : this.plugin.getServer().getOnlinePlayers()) {
                if (!playerTarget.hasPermission("fallcraft.module.essentials.v")) {
                    playerTarget.hidePlayer(player);
                }

            }
            player.setPlayerListName(Ultilities.formater("&4" + player.getName()));
            Bukkit.broadcastMessage(Ultilities.formater("&c-&f &8" + player.getPlayer().getName()));
            player.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cVoce está invisível"));
            PluginInfo.vanishList.add(player);
            PluginInfo.players.get(player).setVanishStatus(VanishStatus.INVISIBLE);
        } else {
            for (final Player playerTarget : this.plugin.getServer().getOnlinePlayers()) {
                playerTarget.showPlayer(player);

            }
            player.setPlayerListName(Ultilities.formater("&f" + player.getName()));
            PluginInfo.vanishList.remove(player);
            player.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&aVoce está visível"));
            Bukkit.broadcastMessage(Ultilities.formater("&a+&f &8" + player.getPlayer().getName()));
            PluginInfo.players.get(player).setVanishStatus(VanishStatus.VISIBLE);
        }


        return true;
    }
}
