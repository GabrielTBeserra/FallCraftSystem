package fallcraftsystem.modules.chest.events;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.chest.utils.ChestsList;
import fallcraftsystem.modules.chest.utils.ToBase64;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClose implements Listener {
    FallCraftSystem plugin;

    public InventoryClose(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player player;
        if (event.getInventory().getName().contains("§fF§bC §5Ender Chest")) {
            player = (Player) event.getInventory().getHolder();
            player.getEnderChest().setContents(event.getInventory().getContents());
            return;
        }
        if (event.getInventory().getName().contains("§fFall§bCraft §9Baú")) {
            player = (Player) event.getInventory().getHolder();

            ItemStack[] contents = event.getInventory().getContents();
            String base64 = ToBase64.itemStackArrayToBase64(contents);

            if (!ChestsList.getChestListFile().contains("chests.players." + player.getName() + ".chest_items")) {
                ChestsList.getChestListFile().createSection("chests.players." + player.getName() + ".chest_items");
            }
            ChestsList.getChestListFile().set("chests.players." + player.getName() + ".chest_items", base64);
            ChestsList.save();
        }

    }
}
