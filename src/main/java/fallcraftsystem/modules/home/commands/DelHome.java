// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.home.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.home.utils.HomeDB;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class DelHome implements CommandExecutor {
    private final static Logger LOGGER = Logger.getLogger(DelHome.class.getName());
    public FallCraftSystem plugin;

    public DelHome(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("delhome").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Commando for players only!");
            return true;
        }
        Player player = (Player) sender;

        String uuidP = player.getUniqueId() + "";
        if (args.length == 2) {
            if (player.hasPermission("fallcraft.home.admin")) {
                uuidP = Ultilities.getUUIDFromNick(args[1]);

            }
        }


        if (args.length < 1) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cInforme o nome da home!"));
            return true;
        }

        System.out.println(HomeDB.getHomeFile().contains(uuidP + ".home." + args[0]));

        if (!HomeDB.getHomeFile().contains(uuidP + ".home." + args[0])) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce nao possui essa home!"));
            return true;
        }
        HomeDB.getHomeFile().set(uuidP + ".home." + args[0], null);
        HomeDB.save();
        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aHome excluida!"));
        LOGGER.info(player.getName() + " execute a /delhome");
        return true;
    }
}
