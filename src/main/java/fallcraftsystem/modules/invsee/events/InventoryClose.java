package fallcraftsystem.modules.invsee.events;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClose implements Listener {
    public FallCraftSystem plugin;

    public InventoryClose(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void inventoryCloseEvent(InventoryCloseEvent event) {
        if (event.getInventory().getName().contains("§fF§bC (§fInventário de §b")){

            Inventory inventory = event.getInventory();
            Player owner = (Player) inventory.getHolder();

            owner.getInventory().setContents(inventory.getContents());
        }
    }
}
