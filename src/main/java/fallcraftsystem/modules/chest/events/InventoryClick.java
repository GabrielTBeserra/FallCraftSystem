package fallcraftsystem.modules.chest.events;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClick implements Listener {
    public FallCraftSystem plugin;

    public InventoryClick(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void EchestClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null) {

            Inventory inventory = event.getClickedInventory();
            Player clicker = (Player) event.getWhoClicked();

            if (inventory.getName().contains("§fF§bC §5Ender Chest  ")) {

                if (clicker.hasPermission("fallcraft.module.echest_mod")
                        && (!clicker.hasPermission("fallcraft.module.echest_admin"))){

                    Player echestOwner = (Player) inventory.getHolder();

                    if (echestOwner != clicker) {
                        event.setCancelled(true);
                    }
                }
            }

            if (inventory.getName().contains("§fFall§bCraft §9Baú")) {

                if (clicker.hasPermission("fallcraft.modules.chest.mod")
                        && (!clicker.hasPermission("fallcraft.modules.chest.admin"))) {

                    Player chestOwner = (Player) inventory.getHolder();

                    if (chestOwner != clicker) {
                        event.setCancelled(true);
                    }
                }
            }

        }

    }
}
