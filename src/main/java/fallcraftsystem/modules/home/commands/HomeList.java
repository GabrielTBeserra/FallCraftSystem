package fallcraftsystem.modules.home.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.home.utils.HomeDB;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.PluginInfo;
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


        StringBuilder stringBuilder = new StringBuilder();
        player.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&aTodas suas homes!"));
        if (HomeDB.getHomeFile().contains(player.getUniqueId() + ".home")) {
            ConfigurationSection neg = HomeDB.getHomeFile().getConfigurationSection(player.getUniqueId() + ".home");

            for (String key : neg.getKeys(false)) {
                player.sendMessage(Ultilities.formater("&a-> &6" + key));
            }
        }


        return true;
    }
}
