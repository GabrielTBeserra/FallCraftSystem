package fallcraftsystem.modules.spycommands.events;

import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.SpyCStatus;
import fallcraftsystem.utils.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Arrays;

public class ChatEvent implements Listener {
    public FallCraftSystem plugin;

    public ChatEvent(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void asyncPlayerChatEvent(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if (!event.getMessage().equals("legendchat")) {
            Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);

            for (Player player1 : players) {
                if (player.hasPermission("fallcraft.module.spyc")) {
                    GamePlayer gp = ServerUtils.players.get(player1);

                    if (gp.getSpyCStatus().equals(SpyCStatus.ON)) {
                        player1.sendMessage("§9§lSpyCommands §b§l>> §e " + player.getDisplayName() + " disse: §c" + event.getMessage());
                    }
                }
            }
        }
    }
}
