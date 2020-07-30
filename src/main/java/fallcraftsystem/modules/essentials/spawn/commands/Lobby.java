package fallcraftsystem.modules.essentials.spawn.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.PlayerTeleport;
import fallcraftsystem.modules.essentials.spawn.utils.SpawnFile;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.ServerUtils;
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

        if (args.length == 1) {
            if (!p.hasPermission("fallcraft.module.essentials.spawn_admin")) {
                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cUse /spawn."));
                return true;
            }

            if (plugin.getServer().getPlayer(args[0]) == null) {
                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player não está online!"));
                return true;
            }

            p = plugin.getServer().getPlayer(args[0]);
        }

        double X = SpawnFile.getSpawnFile().getDouble("LOCALTION.X");
        double Y = SpawnFile.getSpawnFile().getDouble("LOCALTION.Y");
        double Z = SpawnFile.getSpawnFile().getDouble("LOCALTION.Z");
        String world = SpawnFile.getSpawnFile().getString("LOCALTION.WORLD");

        Location l = new Location(plugin.getServer().getWorld(world), X, Y, Z);



        p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Teleportado aguarde! &c(NAO SE MEXA)"));

        if (args.length == 1 && sender.hasPermission("fallcraft.teleport.bypass")) {
            ServerUtils.teleportMap.put(p, new PlayerTeleport(0, p.getLocation(), l));
            return true;
        }

        ServerUtils.teleportMap.put(p, new PlayerTeleport(3, p.getLocation(), l));

        return true;
    }
}
