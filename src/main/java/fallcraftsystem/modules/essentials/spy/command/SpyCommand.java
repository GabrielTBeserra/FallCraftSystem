package fallcraftsystem.modules.essentials.spy.command;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.SpyStatus;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.ServerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpyCommand implements CommandExecutor {
    public FallCraftSystem plugin;

    public SpyCommand(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("spy").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for players!");
            return false;
        }

        Player p = (Player) sender;

        if (!ServerUtils.players.containsKey(p)) {
            GamePlayer gm = new GamePlayer(p);
            ServerUtils.players.put(p, gm);
        }


        if (ServerUtils.players.get(p).getSpyStatus().equals(SpyStatus.ON)) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cSpy disable"));
            ServerUtils.players.get(p).setSpyStatus(SpyStatus.OFF);
        } else if (ServerUtils.players.get(p).getSpyStatus().equals(SpyStatus.OFF)) {
            sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aSpy enable"));
            ServerUtils.players.get(p).setSpyStatus(SpyStatus.ON);
        }


        return true;
    }
}
