package fallcraftsystem.core;

import fallcraftsystem.utils.SaveInventory;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Save implements CommandExecutor {
    public FallCraftSystem plugin;

    public Save(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("savei").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;



        SaveInventory.save(plugin, SaveInventory.InventoryToString(p.getInventory()), p);
        p.getInventory().clear();

        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "clear " + p.getPlayer().getName());

        sender.sendMessage("asd");


        return true;
    }
}
