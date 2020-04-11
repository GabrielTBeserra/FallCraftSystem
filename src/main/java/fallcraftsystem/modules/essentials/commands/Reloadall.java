// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.modules.essentials.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class Reloadall implements CommandExecutor {
    public FallCraftSystem plugin;

    public Reloadall(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("reloadall").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {

        final Player PlayerSender = (Player) sender;


        Bukkit.broadcastMessage(MethodsStatics.formater(PluginInfo.SERVER_NAME + "&aReiniciando o servidor em em:"));

        for (int i = 3; i >= 0; i--) {
            try {
                TimeUnit.SECONDS.sleep(1);
                Bukkit.broadcastMessage(MethodsStatics.formater(PluginInfo.SERVER_NAME + "&c" + i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.kickPlayer(MethodsStatics.formater("&cServer is restarting..."));
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "reload");


        return true;
    }
}
