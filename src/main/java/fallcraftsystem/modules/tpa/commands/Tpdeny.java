package fallcraftsystem.modules.tpa.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.tpa.utils.Utilities;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpdeny implements CommandExecutor {
    public FallCraftSystem plugin;
    public Tpdeny(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpdeny").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para jogadores.");
            return true;
        }

        Player player2 = (Player) sender;

        if (Utilities.getTpa().containsKey(player2)) {

            Player player1 = Utilities.getTpa().get(player2);

            player2.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cPedido de teleporte recusado."));
            player1.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cPedido de teleporte recusado."));

            Utilities.getTpa().remove(player2);
            return true;
        }

        if (Utilities.getTpaHere().containsKey(player2)) {

            Player player1 = Utilities.getTpaHere().get(player2);

            player2.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cPedido de teleporte recusado."));
            player1.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cPedido de teleporte recusado."));

            Utilities.getTpaHere().remove(player2);
            return true;
        }

        player2.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cSem pedidos pendentes."));

        return true;
    }
}
