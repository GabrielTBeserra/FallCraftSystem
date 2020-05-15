package fallcraftsystem.utils;

import fallcraftsystem.core.FallCraftSystem;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
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

    public static String getUuid(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(url));
            if (UUIDJson.isEmpty()) return "invalid name";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            return UUIDObject.get("id").toString();
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }

    public static void setPlayerListHeader(Player player, String header) {
        CraftPlayer cplayer = (CraftPlayer) player;
        PlayerConnection connection = cplayer.getHandle().playerConnection;
        IChatBaseComponent hj = IChatBaseComponent.ChatSerializer.a("{'text':'" + header + "'}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, hj);
            headerField.setAccessible(!headerField.isAccessible());

        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.sendPacket(packet);
    }


    public static void setPlayerListFooter(Player player, String footer) {
        CraftPlayer cp = (CraftPlayer) player;
        PlayerConnection con = cp.getHandle().playerConnection;
        IChatBaseComponent fj = IChatBaseComponent.ChatSerializer.a("{'text':'" + footer + "'}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try {
            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, fj);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.sendPacket(packet);
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
                            if (ServerUtils.teleportMap.get(p).getLocation().equals(p.getLocation())) {
                                if (!p.hasPermission("fallcraft.teleport.bypass")) {
                                    if (ServerUtils.teleportMap.get(p).getTime() > 0) {
                                        p.sendMessage(Ultilities.formater("&6&lTELEPORT &9>> &CTeleportando em " + ServerUtils.teleportMap.get(p).getTime()));
                                        ServerUtils.teleportMap.get(p).setTime(ServerUtils.teleportMap.get(p).getTime() - 1);

                                    } else if (ServerUtils.teleportMap.get(p).getTime() == 0) {
                                        p.sendMessage(Ultilities.formater("&6&lTELEPORT &9>> &CTeleportando!"));
                                        p.playSound(ServerUtils.teleportMap.get(p).getToLoc(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                                        p.teleport(ServerUtils.teleportMap.get(p).getToLoc());
                                        ServerUtils.teleportMap.remove(p);
                                    }
                                } else {
                                    p.sendMessage(Ultilities.formater("&6&lTELEPORT &9>> &CTeleportando!"));
                                    p.playSound(ServerUtils.teleportMap.get(p).getToLoc(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                                    p.teleport(ServerUtils.teleportMap.get(p).getToLoc());
                                    ServerUtils.teleportMap.remove(p);
                                }
                            } else {
                                p.sendMessage(Ultilities.formater("&6&lTELEPORT &9>> &CVoce se mexeu, cancelando teleport!"));
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
