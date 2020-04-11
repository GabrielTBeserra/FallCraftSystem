// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.FlyStatus;
import fallcraftsystem.utils.PluginInfo;
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
        final Player player = (Player) sender;

        GamePlayer gm = PluginInfo.players.get(player);

        if (gm.getFlyStatus().equals(FlyStatus.NOT_FLYING)) {
            player.sendMessage("Voce esta voando agora");
            player.setAllowFlight(true);
            PluginInfo.players.get(player).setFlyStatus(FlyStatus.FLYING);
        } else {
            player.setAllowFlight(false);
            player.sendMessage("Voce esta nao voando agora");
            PluginInfo.players.get(player).setFlyStatus(FlyStatus.NOT_FLYING);
        }


        return true;
    }
}
