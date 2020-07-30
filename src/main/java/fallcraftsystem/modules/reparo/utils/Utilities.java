package fallcraftsystem.modules.reparo.utils;

import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.dependencies.VaultEconomy;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class Utilities {
    public static int getPermIndex(CommandSender sender) {
        int quantidade = ReparoConfig.getRepararFile().getInt("config.precos.quantidade");
        int index = 0;
        for (int i = 1; i <= quantidade; i++) {
            if (sender.hasPermission("fallcraft.module.reparar_" + i)) {
                index = i;
                break;
            }
        }
        return index;
    }


    public static int handOrAll(String[] args) {
        int handall = 0;

        if (args[0].equalsIgnoreCase("mao") || args[0].equalsIgnoreCase("mão") || args[0].equalsIgnoreCase("hand")) {
            handall = 1;

        } else if (args[0].equalsIgnoreCase("tudo") || args[0].equalsIgnoreCase("all")) {
            handall = 2;

        }

        return handall;
    }



    public static boolean hasEnoughMoney(double money, double price) {
        return money > price;
    }


    public static void repair(int handall, Player player, boolean bypass, double price) {

        switch (handall) {

            case 1:
                player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aReparando item..."));

                ItemStack hand = player.getItemInHand();
                hand.setDurability((short) 100000);

                break;

            case 2:
                player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aReparando itens..."));

                ItemStack[] itens = player.getInventory().getContents();
                List<Material> filter = getFilter();

                int quantidade = 0;

                for(ItemStack item : itens) {
                    if (item != null) {
                        if (filter.contains(item.getType())) {
                            item.setDurability((short) 100000);
                            quantidade++;
                        }
                    }
                }

                price = price * quantidade;

                break;
        }

        String simbolo = ReparoConfig.getRepararFile().getString("config.simbolo");
        if (!bypass) {
            VaultEconomy.getVault().withdrawPlayer(player.getDisplayName(), price);
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&7Removidos &a" + simbolo + price + "&7 da sua conta."));

        }

    }

    public static List<Material> getFilter() {
        List<Material> items = new java.util.ArrayList<>(Collections.emptyList());

        items.add(Material.DIAMOND_AXE);
        items.add(Material.DIAMOND_BOOTS);
        items.add(Material.DIAMOND_CHESTPLATE);
        items.add(Material.DIAMOND_HELMET);
        items.add(Material.DIAMOND_HOE);
        items.add(Material.DIAMOND_LEGGINGS);
        items.add(Material.DIAMOND_PICKAXE);
        items.add(Material.DIAMOND_SPADE);
        items.add(Material.DIAMOND_SWORD);

        items.add(Material.IRON_AXE);
        items.add(Material.IRON_BOOTS);
        items.add(Material.IRON_CHESTPLATE);
        items.add(Material.IRON_HELMET);
        items.add(Material.IRON_HOE);
        items.add(Material.IRON_LEGGINGS);
        items.add(Material.IRON_PICKAXE);
        items.add(Material.IRON_SPADE);
        items.add(Material.IRON_SWORD);

        items.add(Material.GOLD_AXE);
        items.add(Material.GOLD_BOOTS);
        items.add(Material.GOLD_CHESTPLATE);
        items.add(Material.GOLD_HELMET);
        items.add(Material.GOLD_HOE);
        items.add(Material.GOLD_LEGGINGS);
        items.add(Material.GOLD_PICKAXE);
        items.add(Material.GOLD_SPADE);
        items.add(Material.GOLD_SWORD);

        items.add(Material.STONE_AXE);
        items.add(Material.CHAINMAIL_BOOTS);
        items.add(Material.CHAINMAIL_CHESTPLATE);
        items.add(Material.CHAINMAIL_HELMET);
        items.add(Material.STONE_HOE);
        items.add(Material.CHAINMAIL_LEGGINGS);
        items.add(Material.STONE_PICKAXE);
        items.add(Material.STONE_SPADE);
        items.add(Material.STONE_SWORD);

        items.add(Material.WOOD_AXE);
        items.add(Material.LEATHER_BOOTS);
        items.add(Material.LEATHER_CHESTPLATE);
        items.add(Material.LEATHER_HELMET);
        items.add(Material.WOOD_HOE);
        items.add(Material.LEATHER_LEGGINGS);
        items.add(Material.WOOD_PICKAXE);
        items.add(Material.WOOD_SPADE);
        items.add(Material.WOOD_SWORD);

        return items;
    }
}
