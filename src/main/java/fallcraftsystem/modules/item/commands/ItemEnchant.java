package fallcraftsystem.modules.item.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.item.utils.Item;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemEnchant implements CommandExecutor {
    public FallCraftSystem plugin;
    public ItemEnchant(FallCraftSystem plugin){
        this.plugin = plugin;
        plugin.getCommand("itemenchant").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Ultilities.formater("§7Comando exclusivo aos players");
            return true;
        }

        Player player = (Player) sender;
        Item item = new Item(player.getItemInHand());

        if(item.isAir()){
            Ultilities.send(player, "§cVocê precisa estar segurando um item.");
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("all")) {
                player.sendMessage("§7Digite: §9/itemenchant all <valor>");
                return true;
            }
            if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("remover")) {
                player.sendMessage("§7Digite: §9/itemenchant remover <encantamento>");
                return true;
            }
            player.sendMessage("§cEncantamento inválido!");
            return true;

        }
        if (args.length != 0){
            boolean remove = args[0].equalsIgnoreCase("remover") || args[0].equalsIgnoreCase("remove");
            int nivel = remove ? 0 : Integer.parseInt(args[1]);
            String enchant = remove ? args[1] : args[0];

            item.Enchant(item, enchant, nivel, player);

            return true;
        }
        player.sendMessage("§7Digite §9/itemenchant <encantamento> <nível>");
        player.sendMessage("§7ou §9/itemenchant <remove> <encantamento>\n");
        player.sendMessage("§7Digite §9/itemajuda enchant §7para ver os encantamentos.");

        return true;
    }
}
