package fallcraftsystem.modules.tpa.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.PlayerTeleport;
import fallcraftsystem.modules.tpa.utils.TpaConfig;
import fallcraftsystem.modules.tpa.utils.Utilities;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpaccept implements CommandExecutor {
    public FallCraftSystem plugin;
    public Tpaccept(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpaccept").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para players.");
            return true;
        }

        Player player2 = (Player) sender;
        int tempo = TpaConfig.getTpaFile().getInt("config.tempo_teleporte");

        if (Utilities.getTpa().containsKey(player2)) {
            Player player1 = Utilities.getTpa().get(player2);

            if (player1 != null && player1.isOnline()){

                player2.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Teleportando em &c5 &6segundos..."));
                player1.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Teleportando em &c5 &6segundos..."));

                ServerUtils.teleportMap.put(player1, new PlayerTeleport(tempo-1, player1.getLocation(), player2.getLocation()));

                Utilities.getTpa().remove(player2);
            }
        } else if (Utilities.getTpaHere().containsKey(player2)){

            Player player1 = Utilities.getTpaHere().get(player2);

            if (player1 != null && player1.isOnline()){


                if (player1.hasPermission("fallcraft.teleport.bypass")) {

                    ServerUtils.teleportMap.put(player2, new PlayerTeleport(0, player2.getLocation(), player1.getLocation()));

                } else {

                    player2.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Teleportando em &c5 &6segundos..."));
                    player1.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Teleportando em &c5 &6segundos..."));

                    ServerUtils.teleportMap.put(player2, new PlayerTeleport(tempo-1, player2.getLocation(), player1.getLocation()));

                }

                Utilities.getTpaHere().remove(player2);
            }

        } else {
            player2.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cSem pedidos pendentes."));
        }

        return true;
    }
}
