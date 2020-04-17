package fallcraftsystem.modules.npc.core;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.MethodsStatics;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoadNPC {

    private static final List<EntityPlayer> NPC = new ArrayList<EntityPlayer>();
    private FallCraftSystem plugin;

    public void createNPC(Player player, String npcName, FallCraftSystem plugin) {
        this.plugin = plugin;
        Location location = player.getLocation();
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) player.getWorld()).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "§a§l" + npcName);
        changeSkin(gameProfile);
        EntityPlayer npc = new EntityPlayer(nmsServer, nmsWorld, gameProfile, new PlayerInteractManager(nmsWorld));
        String texture = "eyJ0aW1lc3RhbXAiOjE1ODcwMDU0ODg3MTYsInByb2ZpbGVJZCI6IjA2OWE3OWY0NDRlOTQ3MjZhNWJlZmNhOTBlMzhhYWY1IiwicHJvZmlsZU5hbWUiOiJOb3RjaCIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjkyMDA5YTQ5MjViNThmMDJjNzdkYWRjM2VjZWYwN2VhNGM3NDcyZjY0ZTBmZGMzMmNlNTUyMjQ4OTM2MjY4MCJ9fX0=";
        String signature = "WF3fFnz8cq/H60qNiUNcSujbWMxWkB0O7B/XreDRP8f8PtxGO6pQWAHgjlRM56QwZ7GZFlIIdoELUtUweLRXGaQ+E/f0WZE2yGKUdlzjCFKL75Ita8ACjioRm0dn+2e5u1yKs8lZrKLBaeDSQslLYfwtGZFB3cM44iiBLf1vqOMLYw/gbn1kq83GwzwcmVRCCNoMhjhb+GGOnMk9lhHAEnnZa5N97pTY9uT09RBvXnxZQ3afhMoZFlM5cAdjfjWTKMpodN+LaKna1OZxSc0l4utke6+yBcKhcU5l2FrgKySLjjAn4iWODuI/xU3KNGxhuTpPJlsjtGxkcSnL2EixbCDfl4o+iQgxhAfuB8sNE6rMkGi5Z+cHH6RKG7hak6k1fnAB3I1FOSTls6GIK9WPyX14ypW+xMTr4gAH6BvCc/S5Hby1dBUqoHR1CYTHntdJw89eRzOv+hCSC/+kpopWtdIRtpjd22DKDkPZPpvk5RW3f9KEJqeGMn/QaTKjuVFO1PFru4ECdK5+Df4pctgA/Qqn3UsmeN6EophiIj9BV/Wlo8WY8j0l/c5yTWWMfh211TLdOE5vdnRBUbMXTT+L5AI8ocGUpf/roVHqS+EJbT75yuNNYgr/Vuz8VUJXs8Xd4KEdmL+vcYi8tjhqY5uzUk5uDlzTEp4Pdi/jE+knTXs=";
        npc.getProfile().getProperties().put("textures", new Property("textures", texture, signature));
        Player npcPlayer = npc.getBukkitEntity().getPlayer();
        npcPlayer.setPlayerListName("");

        npc.setLocation(location.getX(), location.getY(), location.getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());

        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
        }
    }


    private void changeSkin(GameProfile profile) {
        String texture = "eyJ0aW1lc3RhbXAiOjE1ODcwMDU0ODg3MTYsInByb2ZpbGVJZCI6IjA2OWE3OWY0NDRlOTQ3MjZhNWJlZmNhOTBlMzhhYWY1IiwicHJvZmlsZU5hbWUiOiJOb3RjaCIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjkyMDA5YTQ5MjViNThmMDJjNzdkYWRjM2VjZWYwN2VhNGM3NDcyZjY0ZTBmZGMzMmNlNTUyMjQ4OTM2MjY4MCJ9fX0=";
        String signature = "WF3fFnz8cq/H60qNiUNcSujbWMxWkB0O7B/XreDRP8f8PtxGO6pQWAHgjlRM56QwZ7GZFlIIdoELUtUweLRXGaQ+E/f0WZE2yGKUdlzjCFKL75Ita8ACjioRm0dn+2e5u1yKs8lZrKLBaeDSQslLYfwtGZFB3cM44iiBLf1vqOMLYw/gbn1kq83GwzwcmVRCCNoMhjhb+GGOnMk9lhHAEnnZa5N97pTY9uT09RBvXnxZQ3afhMoZFlM5cAdjfjWTKMpodN+LaKna1OZxSc0l4utke6+yBcKhcU5l2FrgKySLjjAn4iWODuI/xU3KNGxhuTpPJlsjtGxkcSnL2EixbCDfl4o+iQgxhAfuB8sNE6rMkGi5Z+cHH6RKG7hak6k1fnAB3I1FOSTls6GIK9WPyX14ypW+xMTr4gAH6BvCc/S5Hby1dBUqoHR1CYTHntdJw89eRzOv+hCSC/+kpopWtdIRtpjd22DKDkPZPpvk5RW3f9KEJqeGMn/QaTKjuVFO1PFru4ECdK5+Df4pctgA/Qqn3UsmeN6EophiIj9BV/Wlo8WY8j0l/c5yTWWMfh211TLdOE5vdnRBUbMXTT+L5AI8ocGUpf/roVHqS+EJbT75yuNNYgr/Vuz8VUJXs8Xd4KEdmL+vcYi8tjhqY5uzUk5uDlzTEp4Pdi/jE+knTXs=";
        profile.getProperties().put("textures", new Property("textures", texture, signature));

    }
}
