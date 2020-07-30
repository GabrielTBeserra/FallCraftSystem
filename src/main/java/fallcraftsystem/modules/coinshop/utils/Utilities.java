package fallcraftsystem.modules.coinshop.utils;

import fallcraftsystem.modules.chest.utils.ToBase64;
import fallcraftsystem.modules.coin.database.CoinData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class Utilities {
    public static Inventory createShop(Player player) {
        Inventory coinshop = Bukkit.createInventory(null, 9*3, "§fFall§bCraft §6Coin Shop");

        coinshop.setItem(10, getBuyItem().getItemStack());
        coinshop.setItem(16, getSellItem().getItemStack());
        coinshop.setItem(4, getTutorialItem().getItemStack());

        return setCoinItem(player, coinshop);
    }

    public static Item getBuyItem() {
        List<String> desc = new java.util.ArrayList<>(Collections.emptyList());
        desc.add("§7Clique para ver itens a venda");

        return new Item("§b§lComprar", desc, Material.DIAMOND, (short) 0);
    }

    public static Item getSellItem() {
        List<String> desc = new java.util.ArrayList<>(Collections.emptyList());
        desc.add("§7Clique para ver itens que");
        desc.add("§7você pode vender");

        return new Item("§c§lVender", desc, Material.EMERALD, (short) 0);
    }

    public static Item getTutorialItem() {
        List<String> desc = new java.util.ArrayList<>(Collections.emptyList());
        desc.add("§7Botão Esquerdo: §eCompra 1 / Vende 1");
        desc.add("§7Botão Direito: §eCompra pack / Vende 1");

        return new Item("§9§lAjuda", desc, Material.PAPER, (short) 0);
    }

    public static Item getCoinItem(double coins) {
        List<String> desc = new java.util.ArrayList<>(Collections.emptyList());
        desc.add(String.valueOf("§a¢" + coins));

        return new Item("§a§lSuas Coins:", desc, Material.GOLD_INGOT, (short) 0);
    }

    public static Item getAlreadyItem(String comprarVender) {
        List<String> desc = new java.util.ArrayList<>(Collections.emptyList());
        desc.add(String.valueOf("§7Você já está aqui."));

        return new Item("§7§l" + comprarVender, desc, Material.IRON_INGOT, (short) 0);
    }

    public static Inventory createBuyShop(Player player) {
        Inventory coinshop = Bukkit.createInventory(null, 9*3, "§fFall§bCraft §6Coin Shop §aComprar");

        if(CoinShopItems.getCoinShopItemsFile().contains("items.buy")) {
            try {

                ItemStack[] contents = ToBase64.itemStackArrayFromBase64(CoinShopItems.getCoinShopItemsFile().getString("items.buy"));
                coinshop.setContents(contents);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            CoinShopItems.getCoinShopItemsFile().createSection("items.buy");
            CoinShopItems.getCoinShopItemsFile().set("items.buy", ToBase64.itemStackArrayToBase64(coinshop.getContents()));
        }

        coinshop.setItem(26, getSellItem().getItemStack());
        coinshop.setItem(18, getAlreadyItem("Comprar").getItemStack());

        return setCoinItem(player, coinshop);
    }

    public static Inventory createSellShop(Player player) {
        Inventory coinshop = Bukkit.createInventory(null, 9*3, "§fFall§bCraft §6Coin Shop §cVender");

        if(CoinShopItems.getCoinShopItemsFile().contains("items.sell")) {
            try {

                ItemStack[] contents = ToBase64.itemStackArrayFromBase64(CoinShopItems.getCoinShopItemsFile().getString("items.sell"));
                coinshop.setContents(contents);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            CoinShopItems.getCoinShopItemsFile().createSection("items.sell");
            CoinShopItems.getCoinShopItemsFile().set("items.sell", ToBase64.itemStackArrayToBase64(coinshop.getContents()));
            CoinShopItems.save();
        }


        coinshop.setItem(18, getBuyItem().getItemStack());
        coinshop.setItem(26, getAlreadyItem("Vender").getItemStack());

        return setCoinItem(player, coinshop);
    }

    public static void removeItem(int index, boolean buy, Player player) {
        Inventory shop = buy ? createBuyShop(player) : createSellShop(player);
        index--;

        if (shop.getItem(index) != null) {
            boolean erase = true;
            for (int i = 0; i < shop.getContents().length; i++) {
                if ((shop.getItem(index+1) == null) && erase) {
                    if (shop.getItem(index) != null) {

                        ItemStack item = shop.getItem(index);
                        item.setType(Material.AIR);

                        shop.setItem(index, item);
                        break;
                    }
                }

                if (i > index) {
                    ItemStack itemStack = shop.getItem(i);
                    shop.setItem(i-1, itemStack);
                    erase = false;

                    if (shop.getItem(index).getType().equals(Material.AIR) || shop.getItem(index) == null) {
                        break;
                    }
                }
            }

            String buysell = buy ? "buy" : "sell";

            CoinShopItems.getCoinShopItemsFile().set("items." + buysell, ToBase64.itemStackArrayToBase64(shop.getContents()));
            CoinShopItems.save();
        }

    }

    public static double charArrayToDouble(char[] charArray){
        String string = "";

        for (char charactere : charArray) {
            if (Character.isDigit(charactere) || String.valueOf(charactere).equalsIgnoreCase(".")) {
                string += charactere;
            }
        }

        return Double.parseDouble(string);
    }

    public static ItemStack getActualItem(ItemStack itemStack, int quantidade) {
        Material type = itemStack.getType();
        short id = itemStack.getDurability();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Collections.emptyList());

        ItemStack actualItem = new ItemStack(type, quantidade, id);
        actualItem.setItemMeta(itemMeta);


        return actualItem;
    }

    public static boolean isValid(ItemStack[] contents, ItemStack clickedItem, int quantidadeClicada) {
        Material type = clickedItem.getType();
        int quantidade = 0;

        for(ItemStack itemStack : contents) {
            if (itemStack != null) {
                if (itemStack.getType().equals(type)
                        && itemStack.getDurability() == clickedItem.getDurability()
                        && itemStack.getEnchantments().equals(clickedItem.getEnchantments())) {

                    quantidade += itemStack.getAmount();
                }
            }
        }

        return quantidade >= quantidadeClicada;
    }

    public static void removeSoldItems(Player player, ItemStack[] contents, ItemStack clickedItem) {
        Material type = clickedItem.getType();

        for (ItemStack itemStack : contents) {
            if (itemStack != null) {
                if (itemStack.getType().equals(type)
                        && itemStack.getDurability() == clickedItem.getDurability()
                        && itemStack.getEnchantments().equals(clickedItem.getEnchantments())) {
                    if (itemStack.getAmount() != 1) {
                        itemStack.setAmount(itemStack.getAmount() - 1);
                        return;
                    }

                    if (itemStack.getAmount() == 1) {
                        player.getInventory().removeItem(itemStack);
                        return;
                    }
                    return;
                }
            }
        }
    }

    public static Inventory setCoinItem(Player player, Inventory coinshop) {
        double coins = 0;

        try {
            coins = CoinData.getCoins(player);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        coinshop.setItem(coinshop.getSize() - 5, getCoinItem(coins).getItemStack());

        return coinshop;
    }

}
