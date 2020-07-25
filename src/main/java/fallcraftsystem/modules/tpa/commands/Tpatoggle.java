package fallcraftsystem.modules.tpa.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.TpaStatus;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpatoggle implements CommandExecutor {
    public FallCraftSystem plugin;
    public Tpatoggle(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpatoggle").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para players.");
            return true;
        }

        if (args.length != 0) return false;

        Player player = (Player) sender;
        GamePlayer gamePlayer = ServerUtils.players.get(player);

        TpaStatus tpaStatus = gamePlayer.getTpaStatus();

        if (tpaStatus.equals(TpaStatus.ON)) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cSeus pedidos de teleporte foram desligados."));
            gamePlayer.setTpaStatus(TpaStatus.OFF);
            return true;
        }

        gamePlayer.setTpaStatus(TpaStatus.ON);
        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aSeus pedidos de teleporte foram ligados. "));

        return true;
    }
}
