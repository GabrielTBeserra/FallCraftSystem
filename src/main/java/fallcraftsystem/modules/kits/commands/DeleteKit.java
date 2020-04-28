package fallcraftsystem.modules.kits.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.kits.utils.KitConfig;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.PluginInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteKit implements CommandExecutor {
    public FallCraftSystem plugin;

    public DeleteKit(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("delkit").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&c&lEsse kit nao existe!"));
            return true;
        }


        KitConfig.getKitFIle().set("kit." + args[0].toLowerCase(), null);
        KitConfig.save();
        sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&c&lKit deletado!"));
        return true;
    }
}
