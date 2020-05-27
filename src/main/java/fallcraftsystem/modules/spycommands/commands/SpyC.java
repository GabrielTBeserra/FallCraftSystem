package fallcraftsystem.modules.spycommands.commands;

import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.LuzStatus;
import fallcraftsystem.entities.enums.SpyCStatus;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpyC implements CommandExecutor {
    public FallCraftSystem plugin;

    public SpyC(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("spycommands").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para players");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 0) {
            player.sendMessage("Sintaxe inválida");
            return false;
        }


        GamePlayer gm = ServerUtils.players.get(player);

        if (gm.getSpyCStatus().equals(SpyCStatus.OFF)) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aSpyCommands ligado"));

            ServerUtils.players.get(player).setSpyCStatus(SpyCStatus.ON);
        } else {

            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cSpyCommands desligado"));
            ServerUtils.players.get(player).setSpyCStatus(SpyCStatus.OFF);
        }
        return true;
    }
}
