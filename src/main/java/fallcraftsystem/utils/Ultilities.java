package fallcraftsystem.utils;

import fallcraftsystem.core.FallCraftSystem;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.UUID;

public class Ultilities {
    public static String formater(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void send(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void send(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static String getUUIDFromNick(String name) {
        FallCraftSystem pl = FallCraftSystem.plugin;

        // Verifica se o servidor esta rodando com online mode on ou off
        if (pl.getServer().getOnlineMode()) {
            return returnFromWebSite(name);
        } else {
            return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8)).toString();
        }
    }

    private static String returnFromWebSite(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        String uuidFormated = "";
        String uuid = "";
        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(url));
            if (UUIDJson.isEmpty()) return "invalid name";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            uuid = UUIDObject.get("id").toString();
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        uuidFormated = uuid.replaceAll(
                "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                "$1-$2-$3-$4-$5");


        return uuidFormated;
    }

    public static void sendHeaderAndFooter(Player p, String head, String foot) {
        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
        IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{'color':'', 'text':'" + head + "'}");
        IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{'color':'', 'text':'" + foot + "'}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());

            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.sendPacket(packet);
    }

    public static void teleportTimer() {
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    try {
                        if (ServerUtils.teleportMap.containsKey(p)) {
                            if (!p.hasPermission("fallcraft.teleport.bypass")) {
                                if (ServerUtils.teleportMap.get(p).getLocation().equals(p.getLocation()) || ServerUtils.teleportMap.get(p).isTeleported()) {
                                    if (ServerUtils.teleportMap.get(p).getTime() > 0) {
                                        p.sendMessage(Ultilities.formater("&6&lTELEPORT &9>> &CTeleportando em " + ServerUtils.teleportMap.get(p).getTime()));
                                        ServerUtils.teleportMap.get(p).setTime(ServerUtils.teleportMap.get(p).getTime() - 1);

                                    } else if (ServerUtils.teleportMap.get(p).getTime() == 0) {
                                        p.sendMessage(Ultilities.formater("&6&lTELEPORT &9>> &CTeleportando!"));
                                        p.playSound(ServerUtils.teleportMap.get(p).getToLoc(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                                        p.teleport(ServerUtils.teleportMap.get(p).getToLoc());

                                        if (ServerUtils.teleportMap.get(p).getInvincibility() < 0) {
                                            ServerUtils.teleportMap.get(p).setTeleported(true);
                                            ServerUtils.teleportMap.get(p).setInvincibility(2);
                                        } else if (ServerUtils.teleportMap.get(p).getInvincibility() > 0) {
                                            ServerUtils.teleportMap.get(p).setInvincibility(ServerUtils.teleportMap.get(p).getInvincibility() - 1);
                                        } else if (ServerUtils.teleportMap.get(p).getInvincibility() == 0) {
                                            ServerUtils.teleportMap.remove(p);
                                        }
                                    }
                                } else {
                                    p.sendMessage(Ultilities.formater("&6&lTELEPORT &9>> &CVoce se mexeu, cancelando teleport!"));
                                    ServerUtils.teleportMap.remove(p);
                                }
                            } else {
                                p.sendMessage(Ultilities.formater("&6&lTELEPORT &9>> &CTeleportando!"));
                                p.playSound(ServerUtils.teleportMap.get(p).getToLoc(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                                p.teleport(ServerUtils.teleportMap.get(p).getToLoc());
                                ServerUtils.teleportMap.remove(p);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        ServerUtils.teleportMap.remove(p);
                    }


                }
            }
        }.runTaskTimer(FallCraftSystem.plugin, 20L, 20L);
    }

}
