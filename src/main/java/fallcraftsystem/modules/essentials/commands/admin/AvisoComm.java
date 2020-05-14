package fallcraftsystem.modules.essentials.commands.admin;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AvisoComm implements CommandExecutor {

    public FallCraftSystem plugin;

    public AvisoComm(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("aviso").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length > 0) {
            String aviso = "";
            for (String arg : args) {
                aviso += arg + " ";
            }

            Bukkit.broadcastMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&8[&cAVISO&8] &f" + aviso));
        }

        return true;
    }
}
