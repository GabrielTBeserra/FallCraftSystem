package fallcraftsystem.modules.item.commands;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SetDurability implements CommandExecutor {
    public FallCraftSystem plugin;
    public SetDurability(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("durability").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para Players");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1){
            player.getItemInHand().setDurability((short) 1);
            return true;
        }
        if (args.length == 1  && args[0].equalsIgnoreCase("all")) {
            Inventory inventory = player.getInventory();

            ItemStack[] items = inventory.getContents();
            for (ItemStack item: items){
                if (item != null){
                    item.setDurability((short) 999);
                }
            }

            ItemStack[] armor = player.getInventory().getArmorContents();
            for (ItemStack armorPart: armor){
                armorPart.setDurability((short) 999);
            }

            return true;
        }
        return false;
    }
}
