package fallcraftsystem.modules.home.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.home.utils.HomeConfig;
import fallcraftsystem.modules.home.utils.HomeDB;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.ServerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SetHome implements CommandExecutor {
    public FallCraftSystem plugin;

    public SetHome(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("sethome").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Commando for players only!");
            return true;
        }
        Player player = (Player) sender;


        if (args.length < 1) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cInforme o nome da home!"));
            return true;
        }

        int maxValue = 0;

        ConfigurationSection sec = HomeConfig.getHomeFile().getConfigurationSection("perms");
        for (String key : sec.getKeys(false)) {
            if (player.hasPermission("fallcraft.module.home." + key)) {

                if (HomeConfig.getHomeFile().getInt("perms." + key) > maxValue) {
                    maxValue = HomeConfig.getHomeFile().getInt("perms." + key);
                }
            }
        }

        if (HomeDB.getHomeFile().contains(player.getUniqueId() + ".home")) {
            ConfigurationSection neg = HomeDB.getHomeFile().getConfigurationSection(player.getUniqueId() + ".home");

            if (neg.getKeys(false).size() >= maxValue
                    && !HomeDB.getHomeFile().contains(player.getUniqueId() + ".home." + args[0])) {
                player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce ja atingiu o seu limite de &6" + maxValue + "&c home!"));
                return true;
            }
        }


        HomeDB.getHomeFile().set(player.getUniqueId() + ".home." + args[0] + ".X", player.getLocation().getX());
        HomeDB.getHomeFile().set(player.getUniqueId() + ".home." + args[0] + ".Y", player.getLocation().getY());
        HomeDB.getHomeFile().set(player.getUniqueId() + ".home." + args[0] + ".Z", player.getLocation().getZ());
        HomeDB.getHomeFile().set(player.getUniqueId() + ".home." + args[0] + ".WORLD", player.getLocation().getWorld().getName());
        HomeDB.getHomeFile().set(player.getUniqueId() + ".home." + args[0] + ".DATA" , java.time.LocalDateTime.now().toString());
        HomeDB.save();


        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aNova home definida!"));


        return true;
    }
}
