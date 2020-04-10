package fallcraftsystem.core;

import fallcraftsystem.utils.SaveInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Recovery implements CommandExecutor {
    public FallCraftSystem plugin;

    public Recovery(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("rec").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        ;


        ItemStack[] a = SaveInventory.StringToInventory(SaveInventory.recovery(plugin , p)).getContents();

        p.getInventory().setContents(a);

        //SaveInventory.save(plugin, si, p);

        sender.sendMessage("asd");


        return true;
    }
}
