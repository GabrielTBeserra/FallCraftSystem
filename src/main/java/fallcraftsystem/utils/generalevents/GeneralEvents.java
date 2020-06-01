package fallcraftsystem.utils.generalevents;

import com.massivecraft.factions.entity.MPlayer;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.PvpStatus;
import fallcraftsystem.utils.SendTitle;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.dependencies.ChatVault;
import fallcraftsystem.utils.dependencies.WG;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GeneralEvents implements Listener {
    public FallCraftSystem plugin;

    public GeneralEvents(FallCraftSystem pl) {
        this.plugin = pl;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        GamePlayer gm = new GamePlayer(event.getPlayer());
        Player p = event.getPlayer();

        for (Player ol : ServerUtils.vanishList) {
            if (!p.hasPermission("fallcraft.module.essentials.v")) {
                p.hidePlayer(ol);
            }

        }
        ServerUtils.players.put(event.getPlayer(), gm);
        event.setJoinMessage(Ultilities.formater("&a+&f &8" + event.getPlayer().getName()));

        MPlayer mplayer = MPlayer.get(event.getPlayer());
        Ultilities.sendHeaderAndFooter(event.getPlayer(), Ultilities.formater("&b&lFall&f&lCraft\n&c&lV1.0\n&6&9&lSeja muito bem vindo!\n\n")
                , Ultilities.formater("\n\n&e&lLoja:&5 https://fallcraft.buycraft.net/\n&6&lPLAY.FALLCRAFT.COM.BR"));


        String facName = "";


        if (!(mplayer.getFactionTag().equals("") || mplayer.getFactionTag().equals(" "))) {
            facName = "&5[&f" + mplayer.getFactionTag() + "&5] ";
        }


        event.getPlayer().setPlayerListName(
                Ultilities.formater(
                        ChatVault.getChat().getPlayerPrefix(event.getPlayer()) + facName + "&7" + event.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        GamePlayer m = ServerUtils.players.get(event.getPlayer());
        m = null;

        if (event.getPlayer().hasPermission("fallcraft.module.essentials.inv")) {
            event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
        }


        ServerUtils.vanishList.remove(event.getPlayer());

        ServerUtils.players.remove(event.getPlayer());
        event.setQuitMessage(Ultilities.formater("&c-&f &8" + event.getPlayer().getName()));
    }


    @EventHandler
    public void updatePlayerOnMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();


        if (!ServerUtils.players.containsKey(p)) {
            GamePlayer gm = new GamePlayer(event.getPlayer());
            ServerUtils.players.put(event.getPlayer(), gm);
        }


        LocalPlayer localPlayer = WG.getWorldGuardPlugin(plugin).wrapPlayer(p);
        Vector playerVector = localPlayer.getPosition();
        RegionManager regionManager = WG.getWorldGuardPlugin(plugin).getRegionManager(p.getWorld());
        ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(playerVector);

        if (applicableRegionSet.queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
            if (ServerUtils.players.get(p).getPvpStatus().equals(PvpStatus.ON)) {
                ServerUtils.players.get(p).setPvpStatus(PvpStatus.OFF);
                SendTitle.send(p, Ultilities.formater("&cPVP OFF"), "", 5, 5, 5);
            }
        } else {
            if (ServerUtils.players.get(p).getPvpStatus().equals(PvpStatus.OFF)) {
                SendTitle.send(p, Ultilities.formater("&aPVP ON"), "", 5, 5, 5);
                ServerUtils.players.get(p).setPvpStatus(PvpStatus.ON);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerKill(PlayerDeathEvent event) {
        event.setDeathMessage("");
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        List<AntiLabyMod.LabyMod> mods = new ArrayList<>();
        Collections.addAll(mods, AntiLabyMod.LabyMod.values());
        AntiLabyMod.disableMod(e.getPlayer(), mods);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void removeEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (ServerUtils.teleportMap.containsKey(p)) {
                if (ServerUtils.teleportMap.get(p).getInvincibility() > 0) {
                    event.setCancelled(true);
                }
            }
        }

    }

}


