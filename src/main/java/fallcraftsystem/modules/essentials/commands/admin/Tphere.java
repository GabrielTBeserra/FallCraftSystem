package fallcraftsystem.modules.essentials.commands.admin;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tphere implements CommandExecutor {
    public FallCraftSystem plugin;

    public Tphere(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("tphere").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para players.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cUse /tph <jogador>."));
            return true;
        }

        Player admin = (Player) sender;

        if (plugin.getServer().getPlayer(args[0]) == null) {
            admin.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player não está online!"));
            return true;
        }

        Player player = plugin.getServer().getPlayer(args[0]);

        admin.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aTeleportando o jogador " + player.getDisplayName() + "..."));
        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aVocê foi teleportado pela staff."));

        player.teleport(admin.getLocation());

        return true;
    }
}
