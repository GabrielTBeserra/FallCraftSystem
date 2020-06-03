package fallcraftsystem.modules.chest.events;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.chest.utils.ChestsList;
import fallcraftsystem.modules.chest.utils.ToBase64;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

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

            int size = event.getInventory().getSize();
            boolean empty = true;

            for (int i = 0; i < size; i++) {
                if (event.getInventory().getItem(i) != null) {
                    empty = false;
                    break;
                }
            }

            ItemStack[] contents = event.getInventory().getContents();

            if (empty) {
                Inventory inv = Bukkit.createInventory(player, 9);
                contents = inv.getContents();
            }

            String base64 = ToBase64.itemStackArrayToBase64(contents);
            if (!ChestsList.getChestListFile().contains("chests.players." + player.getName() + ".chest_items")) {
                ChestsList.getChestListFile().createSection("chests.players." + player.getName() + ".chest_items");
            }
            ChestsList.getChestListFile().set("chests.players." + player.getName() + ".chest_items", base64);
            ChestsList.save();
        }

    }
}
