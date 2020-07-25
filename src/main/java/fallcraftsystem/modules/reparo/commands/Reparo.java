package fallcraftsystem.modules.reparo.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.dependencies.VaultEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fallcraftsystem.modules.reparo.utils.Utilities.*;

public class Reparo implements CommandExecutor {
    public FallCraftSystem plugin;

    public Reparo(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("reparo").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para jogadores.");
            return true;
        }
        
        Player player = (Player) sender;

        if (args.length == 0 || args.length > 2) return false;

        if (args.length == 2 && player.hasPermission("fallcraft.modules.reparar_admin")) {
            
            if (plugin.getServer().getPlayer(args[1]) == null) {
                player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player não está online!"));
                return true;
            }
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aItens reparados."));
            player = plugin.getServer().getPlayer(args[1]);
        }

        int handall = handOrAll(args);
        boolean bypass = sender.hasPermission("fallcraft.module.reparar_gratis");
        int index = getPermIndex(sender);
        double price = getPrice(handall, index);
        double money = VaultEconomy.getVault().getBalance(player);

        if (index == 0 && !bypass) {
            sender.sendMessage("§cSem permissão.");
            return true;
        }

        if (!hasEnoughMoney(money, price) && !bypass) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&7Dinheiro insuficiente"));
            return true;
        }

        repair(handall, player, bypass, price);

        return true;
    }
}
