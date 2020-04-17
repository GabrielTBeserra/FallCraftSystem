package fallcraftsystem.modules.npc.listener;

import com.massivecraft.factions.event.EventFactionsCreate;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;
import fallcraftsystem.utils.dependencies.WG;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class NpcsEvents implements Listener {
    public FallCraftSystem plugin;

    public NpcsEvents(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnClickNPC(NPCLeftClickEvent event) {
        NPC npc = event.getNPC();
        if (npc.getId() == 1) {
            event.getClicker().sendMessage("OI");
        }
        event.getClicker().sendMessage("ID:" + npc.getId());

    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {
        final String[] message = event.getMessage().toLowerCase().split(" ");
        if (event.getMessage().contains("/f criar") || event.getMessage().contains("/f create")) {
            boolean isInGuild = false;
            Player p = event.getPlayer();
            LocalPlayer localPlayer = WG.getWorldGuardPlugin(plugin).wrapPlayer(p);
            Vector playerVector = localPlayer.getPosition();
            RegionManager regionManager = WG.getWorldGuardPlugin(plugin).getRegionManager(p.getWorld());
            ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(playerVector);

            for (ProtectedRegion a : applicableRegionSet) {
                if (a.getId().equals("guildhall")) {
                    isInGuild = true;
                    break;
                }
            }

            if (!isInGuild) {
                event.setCancelled(true);
                p.sendMessage(MethodsStatics.formater(PluginInfo.SERVER_NAME + "&cVoce precisa esta no guild hall para criar sua faction"));
            }
        }


    }

    @EventHandler
    public void blockInvCreate(EventFactionsCreate event) {
        Player p = event.getMPlayer().getPlayer();

        boolean isInGuild = false;

        LocalPlayer localPlayer = WG.getWorldGuardPlugin(plugin).wrapPlayer(p);
        Vector playerVector = localPlayer.getPosition();
        RegionManager regionManager = WG.getWorldGuardPlugin(plugin).getRegionManager(p.getWorld());
        ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(playerVector);


        for (ProtectedRegion a : applicableRegionSet) {
            if (a.getId().equals("guildhall")) {
                isInGuild = true;
                break;
            }
        }

        if (!isInGuild) {
            event.setCancelled(true);
            p.sendMessage(MethodsStatics.formater(PluginInfo.SERVER_NAME + "&cVoce precisa esta no guild hall para criar sua faction"));
        }


    }


}
