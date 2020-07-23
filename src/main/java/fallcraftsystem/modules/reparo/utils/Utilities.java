package fallcraftsystem.modules.reparo.utils;

import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.dependencies.VaultEconomy;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

        if (args[0].equalsIgnoreCase("mao") || args[0].equalsIgnoreCase("mão")) {
            handall = 1;

        } else if (args[0].equalsIgnoreCase("tudo")) {
            handall = 2;

        }

        return handall;
    }


    public static double getPrice(int handall, int index) {
        switch (handall) {
            case 1:
                return ReparoConfig.getRepararFile().getDouble("config.precos." + index + ".mao");
            case 2:
                return ReparoConfig.getRepararFile().getDouble("config.precos." + index + ".tudo");
            default:
                return 0;
        }
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

                for(ItemStack item : itens) {
                    if (item != null) {
                        item.setDurability((short) 100000);
                    }
                }
                break;
        }

        String simbolo = ReparoConfig.getRepararFile().getString("config.simbolo");
        if (!bypass) {
            VaultEconomy.getVault().withdrawPlayer(player.getDisplayName(), price);
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&7Removidos &a" + simbolo + price + "&7 da sua conta."));

        }

    }
}
