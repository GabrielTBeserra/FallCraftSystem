package fallcraftsystem.modules.craftingtable.command;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CraftingTable implements CommandExecutor {
    public FallCraftSystem plugin;

    public CraftingTable(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("craftingtable").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas para players.");
            return true;
        }

        if (args.length != 0) return false;

        Player player = (Player) sender;
        player.openWorkbench(player.getLocation(), true);
        return true;
    }
}
