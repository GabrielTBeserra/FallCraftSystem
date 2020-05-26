package fallcraftsystem.modules.item.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.item.utils.Item;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class ItemName implements CommandExecutor {
    FallCraftSystem plugin;
    Item item;
    Player player;
    public ItemName(FallCraftSystem plugin) {
        this.plugin = plugin;

        plugin.getCommand("itemnome").setExecutor(this);

    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando exclusivo aos players.");
            return true;
        }

        player = (Player) sender;

        if (args.length > 0) {
            String nome = "";

            item = new Item(player.getItemInHand());
            if (item.isAir()) {
                player.sendMessage("§7Você precisa estar segurando um item.");
                return true;
            }

            for (String arg : args) {
                nome += Ultilities.formater(arg + " ");
            }

            item.setName(nome);
            Ultilities.send(player, "§aNome alterado para " + nome);
            item.setLore(Collections.singletonList(" "));
            return true;
        }
        player.sendMessage("§7Digite §9/itemnome <nome> §7para definir o nome do item.\n");
        player.sendMessage("§7Digite §9/itemajuda §7para ver mais comandos.");

        return true;
    }
}
