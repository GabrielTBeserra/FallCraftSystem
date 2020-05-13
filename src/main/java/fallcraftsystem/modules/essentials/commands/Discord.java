package fallcraftsystem.modules.essentials.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.PluginInfo;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Discord implements CommandExecutor {
    public FallCraftSystem plugin;

    public Discord(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("discord").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }

        sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&6Nosso discord: &9https://discord.gg/Rh63Fjz"));

        return true;
    }
}
