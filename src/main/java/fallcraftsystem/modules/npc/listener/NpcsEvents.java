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
import fallcraftsystem.utils.ServerUtils;
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
            if (!NpcFile.getNpcFile().contains("npc.4" + p.getUniqueId() + "")) {
                NpcFile.getNpcFile().set("npc.4" + p.getUniqueId() + ".conversado", true);

                Ultilities.openBook(Falas.getFala(p, 1), p);
            } else {
                if (NpcFile.getNpcFile().getBoolean("npc.4" + p.getUniqueId() + ".conversado")) {
                    p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce ja falou com esse npc"));
                } else {
                    NpcFile.getNpcFile().set("npc.4" + p.getUniqueId() + ".conversado", true);
                    Ultilities.openBook(Falas.getFala(p, 1), p);

                }
            }
            NpcFile.save();
        } else if (npc.getId() == 13) {
            if (!NpcFile.getNpcFile().contains("npc.13" + p.getUniqueId() + "")) {
                NpcFile.getNpcFile().set("npc.13" + p.getUniqueId() + ".conversado", true);

                Ultilities.openBook(Falas.getFala(p, 2), p);
            } else {
                if (NpcFile.getNpcFile().getBoolean("npc.13" + p.getUniqueId() + ".conversado")) {
                    p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce ja falou com esse npc"));
                } else {
                    NpcFile.getNpcFile().set("npc.13" + p.getUniqueId() + ".conversado", true);
                    Ultilities.openBook(Falas.getFala(p, 2), p);

                }
            }
            NpcFile.save();
        } else if (npc.getId() == 7) {
            if (!NpcFile.getNpcFile().contains("npc.7" + p.getUniqueId() + "")) {
                NpcFile.getNpcFile().set("npc.7" + p.getUniqueId() + ".conversado", true);

                Ultilities.openBook(Falas.getFala(p, 3), p);
            } else {
                if (NpcFile.getNpcFile().getBoolean("npc.7" + p.getUniqueId() + ".conversado")) {
                    p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce ja falou com esse npc"));
                } else {
                    NpcFile.getNpcFile().set("npc.7" + p.getUniqueId() + ".conversado", true);
                    Ultilities.openBook(Falas.getFala(p, 3), p);

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
                p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce precisa esta no guild hall para criar sua faction"));
            } else {
                if (NpcFile.getNpcFile().contains("npc.4" + p.getUniqueId() + "")
                        || NpcFile.getNpcFile().contains("npc.13" + p.getUniqueId() + "")
                        || NpcFile.getNpcFile().contains("npc.7" + p.getUniqueId() + "")) {
                    if (!NpcFile.getNpcFile().getBoolean("npc.4" + p.getUniqueId() + ".conversado")
                            || !NpcFile.getNpcFile().getBoolean("npc.13" + p.getUniqueId() + ".conversado")
                            || !NpcFile.getNpcFile().getBoolean("npc.7" + p.getUniqueId() + ".conversado")) {
                        event.setCancelled(true);
                        p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce precisa conversar com todos os guardioes no guild hall para dar continuidade"));
                    }
                } else {
                    event.setCancelled(true);
                    p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce precisa conversar com todos os guardioes no guild hall para dar continuidade"));
                }

            }

            NpcFile.save();
        }

        boolean isInSpawn = false;
        if (event.getMessage().contains("/f proteger") || event.getMessage().contains("/f claim")) {
            Player p = event.getPlayer();
            LocalPlayer localPlayer = WG.getWorldGuardPlugin(plugin).wrapPlayer(p);
            Vector playerVector = localPlayer.getPosition();
            RegionManager regionManager = WG.getWorldGuardPlugin(plugin).getRegionManager(p.getWorld());
            ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(playerVector);

            for (ProtectedRegion a : applicableRegionSet) {
                if (a.getId().equals("spawn")) {
                    isInSpawn = true;
                    break;
                }
            }


            if (isInSpawn) {
                event.setCancelled(true);
                p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce nao pode construir aqui"));
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
            p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce precisa esta no guild hall para criar sua faction"));
        } else {
            if (NpcFile.getNpcFile().contains("npc.4" + p.getUniqueId() + "")
                    || NpcFile.getNpcFile().contains("npc.13" + p.getUniqueId() + "")
                    || NpcFile.getNpcFile().contains("npc.7" + p.getUniqueId() + "")) {
                if (!NpcFile.getNpcFile().getBoolean("npc.4" + p.getUniqueId() + ".conversado")
                        || !NpcFile.getNpcFile().getBoolean("npc.13" + p.getUniqueId() + ".conversado")
                        || !NpcFile.getNpcFile().getBoolean("npc.7" + p.getUniqueId() + ".conversado")) {
                    event.setCancelled(true);
                    p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce precisa conversar com todos os guardioes no guild hall para dar continuidade"));
                }
            } else {
                event.setCancelled(true);
                p.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce precisa conversar com todos os guardioes no guild hall para dar continuidade"));
            }
        }

        NpcFile.save();
    }

    @EventHandler
    public void blockInvCreate(PlayerJoinEvent event) {

    }

}
