package fallcraftsystem.modules.essentials.spawn.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.spawn.utils.SpawnFile;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobby implements CommandExecutor {
    public FallCraftSystem plugin;

    public SetLobby(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("setlobby").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("For only players");
            return false;
        }

        Player p = (Player) sender;

        Location location = p.getLocation();


        SpawnFile.getSpawnFile().set("LOCALTION.X", location.getX());
        SpawnFile.getSpawnFile().set("LOCALTION.Y", location.getY());
        SpawnFile.getSpawnFile().set("LOCALTION.Z", location.getZ());
        SpawnFile.getSpawnFile().set("LOCALTION.WORLD", location.getWorld().getName());

        SpawnFile.save();

        sender.sendMessage("Lobby salvo");

        return true;
    }
}