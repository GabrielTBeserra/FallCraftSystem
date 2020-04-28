package fallcraftsystem.modules.essentials.spawn.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.spawn.utils.SpawnFile;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.PluginInfo;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lobby implements CommandExecutor {
    public FallCraftSystem plugin;

    public Lobby(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("lobby").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("For only players");
            return false;
        }

        Player p = (Player) sender;

        double X = SpawnFile.getSpawnFile().getDouble("LOCALTION.X");
        double Y = SpawnFile.getSpawnFile().getDouble("LOCALTION.Y");
        double Z = SpawnFile.getSpawnFile().getDouble("LOCALTION.Z");
        String world = SpawnFile.getSpawnFile().getString("LOCALTION.WORLD");

        Location l = new Location(plugin.getServer().getWorld(world), X, Y, Z);

        p.teleport(l);

        Ultilities.send(sender, PluginInfo.SERVER_NAME + "&8Teleported");


        return true;
    }
}
