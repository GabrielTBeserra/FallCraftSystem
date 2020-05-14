package fallcraftsystem.modules.essentials.warp.commands;

import fallcraftsystem.entities.PlayerTeleport;
import fallcraftsystem.modules.essentials.warp.utils.WarpFile;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.ServerUtils;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickItemTeleport implements Listener {
    private FileConfiguration locationFile;

    public ClickItemTeleport() {
        this.locationFile = WarpFile.getWarpFile();
    }

    @EventHandler
    public void selectTeleport(final InventoryClickEvent event) {
        try {
            if (event.getCurrentItem().getType().equals(Material.AIR)) {
                return;
            }
        } catch (Exception e) {
            return;
        }
        if (!ChatColor.stripColor(event.getView().getTitle()).equals("WARPS")) {
            return;
        }
        final String warp = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).toUpperCase();
        final String warpName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
        final double X = Double.parseDouble(this.locationFile.getString("warps." + warp + ".X"));
        final double Y = Double.parseDouble(this.locationFile.getString("warps." + warp + ".Y"));
        final double Z = Double.parseDouble(this.locationFile.getString("warps." + warp + ".Z"));
        final String world = this.locationFile.getString("warps." + warp + ".WOLRD");
        final Location loc = new Location(Bukkit.getWorld(world), X, Y, Z);
        event.setCancelled(true);
        final Player player = (Player) event.getWhoClicked();
        player.closeInventory();
        ServerUtils.teleportMap.put(player, new PlayerTeleport(3, player.getLocation(), loc));


        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Teleportado aguarde! &c(NAO SE MEXA)"));
    }
}
