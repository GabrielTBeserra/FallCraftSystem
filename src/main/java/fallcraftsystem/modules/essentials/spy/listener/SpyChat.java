package fallcraftsystem.modules.essentials.spy.listener;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.enums.SpyStatus;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;

public class SpyChat implements Listener {
    public FallCraftSystem plugin;

    public SpyChat(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onTellEvent(PlayerCommandPreprocessEvent event) {
        String[] msgArgs = event.getMessage().split(" ");
        if (msgArgs[0].equals("/tell")) {

            Player sender = event.getPlayer();
            Player alvo = Bukkit.getPlayer(msgArgs[1]);
            String[] message = Arrays.copyOfRange(msgArgs, 2, msgArgs.length);
            String msg = String.join(" ", message);


            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("fallcraft.module.essentials.spy")
                        && PluginInfo.players.get(player).getSpyStatus().equals(SpyStatus.ON)) {
                    player.sendMessage(
                            MethodsStatics.formater("&c&lSPY &9&l>> &5" + sender.getName() + " &7to &5" + alvo.getName() + "&8: &7" + msg));
                }
            }
        }
    }

}
