package fallcraftsystem.modules.npc.listener;

import com.massivecraft.factions.event.EventFactionsCreate;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.npc.utils.Falas;
import fallcraftsystem.modules.npc.utils.NpcFile;
import fallcraftsystem.utils.PluginInfo;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.dependencies.WG;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class NpcsEvents implements Listener {
    public FallCraftSystem plugin;

    public NpcsEvents(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnClickNPC(NPCRightClickEvent event) {
        Player p = event.getClicker();
        NPC npc = event.getNPC();
        if (npc.getId() == 4) {
            if (!NpcFile.getNpcFile().contains(p.getUniqueId() + "")) {
                NpcFile.getNpcFile().set(p.getUniqueId() + ".conversado", true);
                NpcFile.getNpcFile().set(p.getUniqueId() + ".num", 0);
                p.sendMessage(Ultilities.formater("&e" + Falas.getFala(p, 1)));
            } else {
                if (NpcFile.getNpcFile().getBoolean(p.getUniqueId() + ".conversado")) {
                    p.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cVoce ja falou com esse npc"));
                } else {
                    NpcFile.getNpcFile().set(p.getUniqueId() + ".conversado", true);
                    int i = NpcFile.getNpcFile().getInt(p.getUniqueId() + ".num");

                    if (i == 0) {
                        p.sendMessage(Ultilities.formater("&c&lZAPHYR &9>> &6" + Falas.getFala(p, 1).trim()));
                    }
                }
            }
            NpcFile.save();
        }


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
                p.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cVoce precisa esta no guild hall para criar sua faction"));
            } else {
                if (NpcFile.getNpcFile().contains(p.getUniqueId() + "")) {
                    if (!NpcFile.getNpcFile().getBoolean(p.getUniqueId() + ".conversado")) {
                        event.setCancelled(true);
                        p.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cVoce precisa conversar com o Zaphyr no guild hall para dar continuidade"));
                    }
                } else {
                    event.setCancelled(true);
                    p.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cVoce precisa conversar com o Zaphyr no guild hall para dar continuidade"));
                }

            }

            NpcFile.save();
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
            p.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cVoce precisa esta no guild hall para criar sua faction"));
        } else {
            if (NpcFile.getNpcFile().contains(p.getUniqueId() + "")) {
                if (!NpcFile.getNpcFile().getBoolean(p.getUniqueId() + ".conversado")) {
                    event.setCancelled(true);
                    p.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cVoce precisa conversar com o Zaphyr no guild hall para dar continuidade"));
                }
            } else {
                event.setCancelled(true);
                p.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cVoce precisa conversar com o Zaphyr no guild hall para dar continuidade"));
            }
        }

        NpcFile.save();
    }

    @EventHandler
    public void blockInvCreate(PlayerJoinEvent event) {

    }

}
