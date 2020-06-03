package fallcraftsystem.modules.chest.command;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.chest.utils.ChestCreator;
import fallcraftsystem.modules.chest.utils.ChestsList;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ChestCommand implements CommandExecutor {
    public FallCraftSystem plugin;

    public ChestCommand(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("chest").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas para players");
            return true;
        }

        Player player = (Player) sender;
        Player adm = (Player) sender;
        boolean staff = false;

        if (args.length == 1 && player.hasPermission("fallcraft.modules.chest.mod")) {

            if (plugin.getServer().getPlayer(args[0]) == null) {
                adm.sendMessage("§cPlayer não encontrado");
                return true;
            }
            player = plugin.getServer().getPlayer(args[0]);
            staff = true;
        }

        ChestCreator chest = new ChestCreator(player);
        if (!chest.createChest()) {
            adm.sendMessage("§cO jogador informado não possui chest");
            return true;
        }
        Inventory chestVirtual;
        chestVirtual = chest.getChest();

        if (ChestsList.getChestListFile().contains("chests.players." + player.getName())) {
            chestVirtual = chest.loadChest();
        } else {
            ChestsList.getChestListFile().createSection("chests.players." + player.getName() + ".uuid");
            ChestsList.getChestListFile().set("chests.players." + player.getName() + ".uuid", String.valueOf(Ultilities.getUUIDFromNick(player.getName())));
        }

        if (staff) {
            adm.openInventory(chestVirtual);
            return true;
        }

        player.openInventory(chestVirtual);
        return true;
    }
}
