package fallcraftsystem.modules.invsee.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Invsee implements CommandExecutor {
    public FallCraftSystem plugin;

    public Invsee(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("invsee").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para players.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cUse /invsee <jogador>."));
            return true;
        }

        if (plugin.getServer().getPlayer(args[0]) == null) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player não está online!"));
            return true;
        }

        Player admin = (Player) sender;
        Player player = plugin.getServer().getPlayer(args[0]);

        Inventory inv = createInv(player);
        admin.openInventory(inv);

        return true;
    }

    public Inventory createInv(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 9 * 4, "§fF§bC (§fInventário de §b" + player.getDisplayName() + ")");
        inventory.setContents(player.getInventory().getContents());
        return inventory;
    }
}
