// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.home.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.PlayerTeleport;
import fallcraftsystem.modules.home.utils.HomeDB;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {
    public FallCraftSystem plugin;

    public Home(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("home").setExecutor(this);
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

        String uuidP = player.getUniqueId() + "";
        if (args.length == 2) {
            if (player.hasPermission("fallcraft.home.admin")) {
                uuidP = Ultilities.getUUIDFromNick(args[1]);

            }
        }


        if (!HomeDB.getHomeFile().contains(uuidP + ".home." + args[0])) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce nao possui essa home!"));
            return true;
        }

        int X = HomeDB.getHomeFile().getInt(uuidP + ".home." + args[0] + ".X");
        int Y = HomeDB.getHomeFile().getInt(uuidP + ".home." + args[0] + ".Y");
        int Z = HomeDB.getHomeFile().getInt(uuidP + ".home." + args[0] + ".Z");
        String WORLD = HomeDB.getHomeFile().getString(uuidP + ".home." + args[0] + ".WORLD");
        World world = Bukkit.getWorld(WORLD);
        Location loc = new Location(world, X, Y, Z);

        ServerUtils.teleportMap.put(player, new PlayerTeleport(3, player.getLocation(), loc));


        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Teleportado aguarde! &c(NAO SE MEXA)"));

        return true;
    }
}
