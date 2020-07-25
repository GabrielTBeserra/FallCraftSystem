package fallcraftsystem.modules.tpa.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.tpa.utils.Tpaall;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Tpevento implements CommandExecutor {
    public FallCraftSystem plugin;

    public Tpevento(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpevento").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para players.");
            return true;
        }

        if (args.length != 0) return false;

        Player admin = (Player) sender;

        int playersOn = plugin.getServer().getOnlinePlayers().toArray().length;

        if (playersOn == 1) {
            admin.sendMessage("Sem jogadores online");
            return true;
        }

        HashMap<Integer, Player> players = new HashMap<>();

        int i = 1;

        for (Player player : plugin.getServer().getOnlinePlayers()) {
            if (!player.getName().equalsIgnoreCase(admin.getName())) {
                players.put(i, player);
                i++;
            }
        }

        for (i = 1; i <= players.size(); i++) {
            new Tpaall(players.get(i), admin);
        }

        return true;
    }
}
