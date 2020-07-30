package fallcraftsystem.modules.coinshop.utils;

import fallcraftsystem.modules.chest.utils.ToBase64;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private double price;
    private ItemStack itemStack;
    private String nome;

    public Product(double price, ItemStack itemStack, boolean buy, String nome) {
        this.price = price;
        this.itemStack = itemStack;
        this.nome = nome;

        List<String> desc = new ArrayList<>();
        desc.add("§a¢" + String.valueOf(price));
        Item item = new Item(itemStack);
        item.setDesc(desc);
        item.setName(nome.replace("&", "§"));

        if (buy) {

            ItemStack[] contents = null;
            if (CoinShopItems.getCoinShopItemsFile().contains("items.buy")) {
                try {
                    contents = ToBase64.itemStackArrayFromBase64(CoinShopItems.getCoinShopItemsFile().getString("items.buy")) ;

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else {
                CoinShopItems.getCoinShopItemsFile().createSection("items.buy");
            }

            Inventory emptyInv = Bukkit.createInventory(null, 9*3, "Empty Inventory");

            if (contents != null) {
                emptyInv.setContents(contents);
            }

            emptyInv.addItem(itemStack);

            CoinShopItems.getCoinShopItemsFile().set("items.buy", ToBase64.itemStackArrayToBase64(emptyInv.getContents()));
            CoinShopItems.save();

            return;
        }

        ItemStack[] contents = null;
        if (CoinShopItems.getCoinShopItemsFile().contains("items.sell")) {
            try {
                contents = ToBase64.itemStackArrayFromBase64(CoinShopItems.getCoinShopItemsFile().getString("items.sell")) ;

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            CoinShopItems.getCoinShopItemsFile().createSection("items.sell");
        }

        Inventory emptyInv = Bukkit.createInventory(null, 9*3, "Empty Inventory");

        if (contents != null) {
            emptyInv.setContents(contents);
        }

        emptyInv.addItem(itemStack);

        CoinShopItems.getCoinShopItemsFile().set("items.sell", ToBase64.itemStackArrayToBase64(emptyInv.getContents()));
        CoinShopItems.save();

    }
}
