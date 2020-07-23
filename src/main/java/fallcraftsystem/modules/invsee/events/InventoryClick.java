package fallcraftsystem.modules.invsee.events;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;

public class InventoryClick implements Listener {
    public FallCraftSystem plugin;

    public InventoryClick(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        Inventory inventory = event.getInventory();


        if (inventory.getName().contains("§fF§bC (§fInventário de §b")) {

            if (event.getClick().isShiftClick()) {
                event.setCancelled(true);
                return;
            }

            Player admin = (Player) event.getWhoClicked();
            Player owner = (Player) inventory.getHolder();

            if (!admin.hasPermission("fallcraft.module.invsee_admin")) {
                event.setCancelled(true);
            }

            owner.getInventory().setContents(inventory.getContents());
        }
    }
}
