package fallcraftsystem.modules.essentials.commands.admin;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.ServerUtils;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gm implements CommandExecutor {
    public FallCraftSystem plugin;

    public Gm(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("gm").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(Ultilities.formater("&2Informe 1/2/3"));
            return true;
        }
        final Player player = (Player) sender;
        if (!(args[0] instanceof String)) {
            sender.sendMessage(Ultilities.formater("&2Informe 1/2/3"));
            return true;
        }
        final String s;
        final String mode = s = args[0];
        switch (s) {
            case "0": {
                player.setGameMode(GameMode.SURVIVAL);
                break;
            }
            case "1": {
                player.setGameMode(GameMode.CREATIVE);
                break;
            }
            case "2": {
                player.setGameMode(GameMode.ADVENTURE);
                break;
            }
            case "3": {
                player.setGameMode(GameMode.SPECTATOR);
                break;
            }
            default: {
                sender.sendMessage(Ultilities.formater("&2Informe 1/2/3"));
                break;
            }
        }
        sender.sendMessage(Ultilities.formater( ServerUtils.SERVER_NAME +"&2Modo de jogo atualizado para: &6&l" + player.getGameMode().toString()));
        return true;
    }
}
