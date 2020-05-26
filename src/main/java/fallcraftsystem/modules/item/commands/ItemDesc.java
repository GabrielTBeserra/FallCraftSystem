package fallcraftsystem.modules.item.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.item.utils.Item;
import fallcraftsystem.modules.item.utils.Utilities;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class ItemDesc implements CommandExecutor {
    public FallCraftSystem plugin;
    public ItemDesc(FallCraftSystem plugin){
        this.plugin = plugin;
        plugin.getCommand("itemdesc").setExecutor(this);
    }


    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage("§cComando exclusivo aos players.");
            return true;
        }

        Player player = (Player) sender;

        Item item = new Item(player.getItemInHand());
        if (item.isAir()) {
            Ultilities.send(player, "§7Você precisa estar segurando um item.");
            return true;
        }

        int index;
        String desc;
        List<String> descList;

        if (args.length < 1){
            player.sendMessage("§7Digite §9/itemdesc <linha> <desc> §7para definir a descrição do item.");
            player.sendMessage("§7É recomendado que use §9/itemdesc <desc> para a primeira linha.\n");
            player.sendMessage("§7Digite §9/itemajuda §7para ver mais comandos.");
            return true;
        }

        Ultilities.send(player, "§aDescrição alterada.");

        char[] testNumeric = args[0].toCharArray();
        if (Utilities.isNumeric(testNumeric)){
            index = Utilities.isNumeric(testNumeric) ? (Integer.parseInt(args[0]) - 1) : 1;

            desc = Utilities.setDescLine(args, 1);
            descList = Utilities.getCurrentDesc(item);
            descList.add(index, desc);

            item.setLore(descList);
            return true;
        }
        desc = Utilities.setDescLine(args, 0);
        item.setLore(Collections.singletonList(desc));
        return true;
    }
}
