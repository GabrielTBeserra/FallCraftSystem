package fallcraftsystem.modules.chest.command;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static fallcraftsystem.modules.chest.utils.EnderChest.*;


public class EChest implements CommandExecutor {
    public FallCraftSystem plugin;

    public EChest(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("echest").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para players");
            return true;
        }

        Player staff;
        Player player = (Player) sender;
        Inventory echest;

        if (args.length != 0) {
            if (player.hasPermission("fallcraft.module.echest_mod")){
                if (plugin.getServer().getPlayer(args[0]) != null) {
                    staff = player;
                    player = plugin.getServer().getPlayer(args[0]);
                    echest = createVirtEchest(player, "§8(" + player.getDisplayName() + ")");
                    staff.openInventory(echest);
                    return true;
                }
                return false;
            }
            return false;
        }

        echest = createVirtEchest(player, " ");
        player.openInventory(echest);

        return true;
    }
}
