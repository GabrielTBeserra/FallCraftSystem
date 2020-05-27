package fallcraftsystem.modules.spycommands.events;

import com.massivecraft.factions.entity.MPlayer;
import fallcraftsystem.entities.enums.SpyCStatus;
import fallcraftsystem.utils.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import fallcraftsystem.entities.GamePlayer;

public class CommandEvent implements Listener {
    public FallCraftSystem plugin;

    public CommandEvent(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event){
        String message = event.getMessage();

        Player player = event.getPlayer();
        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);

        for (Player player1 : players) {
            if (player.hasPermission("fallcraft.module.spyc")) {
                GamePlayer gp = ServerUtils.players.get(player1);

                if (gp.getSpyCStatus().equals(SpyCStatus.ON)) {
                    player1.sendMessage("§9§lSpyCommands §b§l>> §e " + player.getDisplayName() + " executou: §c" + message);
                }
            }
        }
    }
}
