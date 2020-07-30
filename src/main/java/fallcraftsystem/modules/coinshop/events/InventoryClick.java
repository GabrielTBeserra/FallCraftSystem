package fallcraftsystem.modules.coinshop.events;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.coin.database.CoinData;
import fallcraftsystem.modules.coinshop.utils.CoinShopItems;
import fallcraftsystem.modules.coinshop.utils.Utilities;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import net.minecraft.server.v1_8_R3.Item;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static fallcraftsystem.modules.coinshop.utils.Utilities.*;

public class InventoryClick implements Listener {
    public FallCraftSystem plugin;

    public InventoryClick(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    Player player;

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null) {

            ClickType clickType = event.getClick();
            Inventory clicked = event.getClickedInventory();
            player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clicked.getName().equalsIgnoreCase(createShop(player).getName())) {

                event.setCancelled(true);
                if (clickedItem.equals(getBuyItem().getItemStack())) {
                    player.closeInventory();
                    player.openInventory(createBuyShop(player));
                }
                if (clickedItem.equals(getSellItem().getItemStack())) {
                    player.closeInventory();
                    player.openInventory(createSellShop(player));
                }
                return;
            }

            int quantidade;
            if (clickType.isLeftClick()) {
                quantidade = 1;
            } else if (clickType.isRightClick()) {
                quantidade = clickedItem.getMaxStackSize();
            } else {
                return;
            }

            double price = 0;

            if (clicked.getName().equalsIgnoreCase(createBuyShop(player).getName())
                    || clicked.getName().equalsIgnoreCase(createSellShop(player).getName())) {

                if (clickedItem.getType().equals(Material.AIR)) {
                    return;
                }

                String priceStrg = clickedItem.getItemMeta().getLore().get(0);
                char[] priceArray = priceStrg.toCharArray();
                price = charArrayToDouble(priceArray) * quantidade;
            }

            if (clicked.getName().equalsIgnoreCase(createBuyShop(player).getName())) {
                event.setCancelled(true);

                if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(getSellItem().getName())) {
                    player.openInventory(createSellShop(player));
                    return;
                }

                if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(getAlreadyItem("Comprar").getName())) {
                    return;
                }

                try {
                    double balance = CoinData.getCoins(player);
                    if (price > balance) {
                        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cCoins insuficientes"));
                        return;
                    }

                    CoinData.takeCoins(player, price);
                    ItemStack actualItem = getActualItem(clickedItem, quantidade);
                    player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§aRemovidos ¢" + price + "§a da sua conta."));
                    player.getInventory().addItem(actualItem);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                player.openInventory(createBuyShop(player));

                return;
            }

            if (clicked.getName().equalsIgnoreCase(createSellShop(player).getName())) {
                event.setCancelled(true);

                if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(getBuyItem().getName())) {
                    player.openInventory(createBuyShop(player));
                    return;
                }

                if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(getAlreadyItem("Vender").getName())) {
                    return;
                }

                price = price / quantidade;
                quantidade = 1;
                if (!isValid(player.getInventory().getContents(), clickedItem, quantidade)) {
                    player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cQuantidade insuficiente."));
                    return;
                }

                try {
                    removeSoldItems(player, player.getInventory().getContents(), clickedItem);
                    player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§aAdicionados ¢" + price + "§a à sua conta."));
                    CoinData.addCoins(player, price);


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                player.openInventory(createSellShop(player));

            }

        }

    }

}
