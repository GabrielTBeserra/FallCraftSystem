package fallcraftsystem.modules.kits.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.kits.core.KitInv;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class CreateKit implements CommandExecutor {
    public static Map<String, KitInv> ListInvsKits = new HashMap<String, KitInv>();
    public FallCraftSystem plugin;

    public CreateKit(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("createkit").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }

        if (args.length < 2) {
            return false;
        }

        Player player = (Player) sender;
        String kitName = args[0].toLowerCase();
        String kitPerm = "fallcraft.module.kit.default";

        Material item = player.getInventory().getItemInHand().getType();
        boolean enchant = player.getInventory().getItemInHand().getEnchantments().size() > 0;

        String itemName = player.getInventory().getItemInHand().getType().toString();
        if (item.equals(Material.AIR)) {
            item = Material.GRASS;
            itemName = "GRASS";
        }

        short type = player.getInventory().getItemInHand().getDurability();


        if (args.length == 4) {
            kitPerm = "fallcraft.module.kit." + args[3].toLowerCase();
        }

        int iconPos = 0;
        int time = 1;

        try {
            iconPos = Integer.parseInt(args[1]);
            time = Integer.parseInt(args[2]);
        } catch (Exception e) {
            sender.sendMessage(MethodsStatics.formater(PluginInfo.SERVER_NAME + "&c&lVoce precisa informar a posicao do item!"));
            return true;
        }

        ListInvsKits.put(kitName, new KitInv(kitName, kitPerm, enchant, itemName, type, iconPos, time));

        Inventory inv = Bukkit.createInventory(player, 27, MethodsStatics.formater("KIT." + kitName));
        player.openInventory(inv);

        return true;
    }
}
