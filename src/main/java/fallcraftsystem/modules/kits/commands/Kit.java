package fallcraftsystem.modules.kits.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.kits.utils.KitConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kit implements CommandExecutor {
    public FallCraftSystem plugin;

    public Kit(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("kit").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }

        Player player = (Player) sender;

        try {
            KitMenu.openInv(player, KitConfig.getKitFIle());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;
    }
}
