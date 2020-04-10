package fallcraftsystem.utils.generalevents;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.FlyStatus;
import fallcraftsystem.entities.enums.PlayerStatus;
import fallcraftsystem.entities.enums.PvpStatus;
import fallcraftsystem.entities.enums.VanishStatus;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;
import fallcraftsystem.utils.SendTitle;
import fallcraftsystem.utils.dependencies.WG;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GeneralEvents implements Listener {
    public FallCraftSystem plugin;

    public GeneralEvents(FallCraftSystem pl) {
        this.plugin = pl;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        GamePlayer gm = new GamePlayer(event.getPlayer(), PlayerStatus.FREE, VanishStatus.VISIBLE, FlyStatus.NOT_FLYING, PvpStatus.OFF);

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


    @EventHandler
    public void updatePlayerOnMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();

        LocalPlayer localPlayer = WG.getWorldGuardPlugin(plugin).wrapPlayer(p);
        Vector playerVector = localPlayer.getPosition();
        RegionManager regionManager = WG.getWorldGuardPlugin(plugin).getRegionManager(p.getWorld());
        ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(playerVector);

        if (applicableRegionSet.queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
            if (PluginInfo.players.get(p).getPvpStatus().equals(PvpStatus.ON)) {
                PluginInfo.players.get(p).setPvpStatus(PvpStatus.OFF);
                SendTitle.send(p, MethodsStatics.formater("&aPVP ON"), "", 5, 5, 5);
            }
        } else {
            if (PluginInfo.players.get(p).getPvpStatus().equals(PvpStatus.OFF)) {
                SendTitle.send(p, MethodsStatics.formater("&cPVP OFF"), "", 5, 5, 5);
                PluginInfo.players.get(p).setPvpStatus(PvpStatus.ON);
            }
        }
    }

}

