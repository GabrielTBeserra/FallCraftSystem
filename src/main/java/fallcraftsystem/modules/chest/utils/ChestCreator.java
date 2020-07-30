package fallcraftsystem.modules.chest.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class ChestCreator {

    public Player player;
    public Inventory chest;

    public ChestCreator(Player player) {
        this.player = player;
        createChest();
    }

    public boolean createChest(){
        int size = 0;
        String name = "          Sem Rank";

        if (player.hasPermission("fallcraft.modules.chest.safira")) {
            size = ChestConfig.getChestFile().getInt("config.tamanhos.safira");
            name = "§d§l           Safira";
            chest = Bukkit.createInventory(player, 9 * size, "§fFall§bCraft §9Baú" + name);
            return true;
        } else if (player.hasPermission("fallcraft.modules.chest.dima")) {
            size = ChestConfig.getChestFile().getInt("config.tamanhos.dima");
            name = "§b§l             Dima";
            chest = Bukkit.createInventory(player, 9 * size, "§fFall§bCraft §9Baú" + name);
            return true;

        } else if (player.hasPermission("fallcraft.modules.chest.ouro")) {
            size = ChestConfig.getChestFile().getInt("config.tamanhos.ouro");
            name = "§6§l             Ouro";
            chest = Bukkit.createInventory(player, 9 * size, "§fFall§bCraft §9Baú" + name);
            return true;
        }
            return false;
    }

    public Inventory getChest() {
        return chest;
    }

    public Inventory loadChest() {

        try {
            String contentsStrg = ChestsList.getChestListFile().getString("chests.players." + player.getName() + ".chest_items");
            ItemStack[] contents = ToBase64.itemStackArrayFromBase64(contentsStrg);
            chest.setContents(contents);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return chest;
    }

}
