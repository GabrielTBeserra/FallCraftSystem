package fallcraftsystem.modules.coinshop.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.coinshop.utils.Product;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

import static fallcraftsystem.modules.coinshop.utils.Utilities.*;


public class CoinShop implements CommandExecutor {
    public FallCraftSystem plugin;

    public CoinShop(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("coinshop").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para players");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.openInventory(createShop(player));
            return true;
        } else if (!player.hasPermission("fallcraft.module.coinshop_admin")) { return false; }
            else {
                if (args[0].equalsIgnoreCase("ajuda")) {
                    player.sendMessage("§cCoinShop Ajuda:\n");
                    player.sendMessage("§e/coinshop add <buy/sell> <preço> <nome>: §9Adiciona o item na aba de compras ou vendas da CoinShop");
                    player.sendMessage("§e/coinshop remove <buy/sell> <número do slot>: §9Remove o item da aba de compras ou vendas da CoinShop");
                    return true;
                }

                if (args.length < 3) {
                    player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cDigite /coinshop ajuda."));
                    return true;
                }

                if (args[0].equalsIgnoreCase("add")) {

                    if (isNumeric(args[2])) {

                        String[] nameArray = Arrays.copyOfRange(args, 3, args.length);
                        String name = String.join(" ", nameArray);

                        if (player.getItemInHand().getType().equals(Material.AIR)) {
                            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cVocê precisa ter um item na mão"));
                            return true;
                        }

                        if (args[1].equalsIgnoreCase("buy")) {
                            new Product(Integer.parseInt(args[2]), player.getItemInHand(), true, name);
                            player.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
                        }
                        else if (args[1].equalsIgnoreCase("sell")) {
                            new Product(Integer.parseInt(args[2]), player.getItemInHand(), false, name);
                            player.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
                        } else {
                            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cDigite /coinshop ajuda para ver os comandos."));
                            return true;
                        }
                        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§aItem adicionado!"));
                        return true;
                    }
                    player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cPreço inválido."));
                    ajuda();
                    return true;
                }

                if (args[0].equalsIgnoreCase("remove")) {
                    if (isNumeric(args[2])) {
                        if (Integer.parseInt(args[2]) > 54) {
                            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cNúmero alto demais."));
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("buy")) {

                            removeItem(Integer.parseInt(args[2]), true, player);
                        }

                        if (args[1].equalsIgnoreCase("sell")) {
                            removeItem(Integer.parseInt(args[2]), false, player);
                        }

                        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§aItem removido."));
                        return true;
                    }
                    player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§cSlot inválido."));
                }


            }
        return true;
    }

    public boolean isNumeric(String arg) {

        char[] argChar = arg.toCharArray();

        for (char charactere : argChar) {
            if (!Character.isDigit(charactere)) {
                return false;
            }
        }
        return true;
    }

    public void ajuda() {

    }

}
