package fallcraftsystem.modules.heathbar.listener;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;

public class AddHearthBarToPlayer implements Listener{
    public FallCraftSystem plugin;
    private Scoreboard scoreboard;

    public AddHearthBarToPlayer(FallCraftSystem plugin , Scoreboard scoreboard){
        this.plugin = plugin;
        this.scoreboard = scoreboard;
        plugin.getServer().getPluginManager().registerEvents(this , plugin);
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e){
        //scoreboard.getTeam("blue").addPlayer(e.getPlayer());
    }
}
