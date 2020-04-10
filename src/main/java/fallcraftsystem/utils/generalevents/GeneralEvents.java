package fallcraftsystem.utils.generalevents;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.FlyStatus;
import fallcraftsystem.entities.enums.PlayerStatus;
import fallcraftsystem.entities.enums.VanishStatus;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GeneralEvents implements Listener {
    public FallCraftSystem plugin;

    public GeneralEvents(FallCraftSystem pl) {
        this.plugin = pl;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        GamePlayer gm = new GamePlayer(event.getPlayer(), PlayerStatus.FREE, VanishStatus.VISIBLE, FlyStatus.NOT_FLYING);

        PluginInfo.players.put(event.getPlayer(), gm);
        event.setJoinMessage(MethodsStatics.formater("&a+&f &8" + event.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        GamePlayer m = PluginInfo.players.get(event.getPlayer());
        m = null;

        PluginInfo.players.remove(event.getPlayer());
        event.setQuitMessage(MethodsStatics.formater("&c-&f &8" + event.getPlayer().getName()));
    }


}
