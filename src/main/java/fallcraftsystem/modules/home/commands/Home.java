// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.home.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.home.utils.HomeDB;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.PluginInfo;
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
            player.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cInforme o nome da home!"));
            return true;
        }

        

        if (!HomeDB.getHomeFile().contains(player.getUniqueId() + ".home." + args[0])) {
            player.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cVoce nao possui essa home!"));
            return true;
        }

        int X = HomeDB.getHomeFile().getInt(player.getUniqueId() + ".home." + args[0] + ".X");
        int Y = HomeDB.getHomeFile().getInt(player.getUniqueId() + ".home." + args[0] + ".Y");
        int Z = HomeDB.getHomeFile().getInt(player.getUniqueId() + ".home." + args[0] + ".Z");
        String WORLD = HomeDB.getHomeFile().getString(player.getUniqueId() + ".home." + args[0] + ".WORLD");
        World wolrd = Bukkit.getWorld(WORLD);
        Location loc = new Location(wolrd, X, Y, Z);
        player.teleport(loc);
        player.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&6Teleportado!"));

        return true;
    }
}
