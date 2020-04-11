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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.TimeUnit;

public class Tpall implements CommandExecutor {
    public FallCraftSystem plugin;

    public Tpall(final FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpall").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                final Player PlayerSender = (Player) sender;

                Bukkit.broadcastMessage(MethodsStatics.formater(PluginInfo.SERVER_NAME + "&aTeleportando todo mundo para o &e" + PlayerSender.getName() + " em:"));


                for (int i = 3; i >= 0; i--) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        Bukkit.broadcastMessage(MethodsStatics.formater(PluginInfo.SERVER_NAME + "&c" + i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.teleport(PlayerSender.getLocation());
                }
            }

        }.runTaskAsynchronously(plugin);


        return true;
    }
}
