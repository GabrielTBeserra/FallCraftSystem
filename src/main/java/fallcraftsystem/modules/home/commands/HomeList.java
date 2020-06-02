package fallcraftsystem.modules.home.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.home.utils.HomeDB;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class HomeList implements CommandExecutor {
    public FallCraftSystem plugin;

    public HomeList(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("homelist").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Commando for players only!");
            return true;
        }
        Player player = (Player) sender;

        String uuidP = player.getUniqueId() + "";
        if (args.length == 1) {
            if (player.hasPermission("fallcraft.home.admin")) {
                uuidP = Ultilities.getUUIDFromNick(args[0]);

            }
        }


        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aTodas suas homes!"));
        if (HomeDB.getHomeFile().contains(uuidP + ".home")) {
            ConfigurationSection neg = HomeDB.getHomeFile().getConfigurationSection(uuidP + ".home");

            for (String key : neg.getKeys(false)) {
                player.sendMessage(Ultilities.formater("&a-> &6" + key));
            }
        }


        return true;
    }
}
