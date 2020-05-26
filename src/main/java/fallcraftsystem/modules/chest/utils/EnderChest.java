package fallcraftsystem.modules.chest.utils;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EnderChest {
    public static Inventory createVirtEchest(Player player, String name){
        Inventory echest = Bukkit.createInventory(player, 9 * 3, "§fF§bC §5Ender Chest  " + name);
        echest.setContents(player.getEnderChest().getContents());

        return echest;
    }
}
