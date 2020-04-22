package fallcraftsystem.modules.kits.commands;

import fallcraftsystem.modules.essentials.warp.utils.STATIC;
import fallcraftsystem.modules.kits.utils.KitDbConfig;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;
import fallcraftsystem.utils.TimeCalculator;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KitMenu {
    public static <TimeStamp> void openInv(final Player player, final FileConfiguration locationFile) throws Exception {
        Inventory inventory = Bukkit.createInventory(player, STATIC.MENU_SIZE, MethodsStatics.formater("&9&lKits"));

        if (!locationFile.contains("kit")) {
            player.sendMessage(MethodsStatics.formater(PluginInfo.SERVER_NAME + "&c&lNenhum kit definido"));
            return;
        }

        ConfigurationSection sec = locationFile.getConfigurationSection("kit");
        ArrayList<ItemStack> itensList = new ArrayList<ItemStack>();


        for (final String key : sec.getKeys(false)) {

                ItemStack item = new ItemStack(Material.getMaterial(locationFile.getString("kit." + key + ".icon")));
                item.setDurability((short) locationFile.getInt("kit." + key + ".type"));


                if (locationFile.getBoolean("kit." + key + ".effect")) {
                    item.addEnchantment(Enchantment.DURABILITY, 1);
                }

                ItemMeta itemMeta = item.getItemMeta();

                String itemName = "&b&o" + StringUtils.capitalize(locationFile.getString("kit." + key + ".name"));

                if (KitDbConfig.getDBKItFile().contains(player.getUniqueId() + "." + key)) {
                    String hora = KitDbConfig.getDBKItFile().getString(player.getUniqueId() + "." + key + ".time");
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hora);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date date = new Date();


                    int difEmMin = (int) TimeCalculator.diferencaEmMinutos(date1, date);
                    int horaTotal = locationFile.getInt("kit." + key + ".time");
                    int minutosTotal = horaTotal * 60;
                    int minutosRestantes = minutosTotal - difEmMin;

/*
                    if (1 > 0) {
                        itemName = "&c&o" + StringUtils.capitalize(locationFile.getString("kit." + key + ".name"));
                    }
*/

                    int horas = minutosRestantes / 60;
                    if (minutosRestantes / 60 > 24) {
                        horas = (((horaTotal - (horaTotal - (minutosRestantes / 1440)))) * 60 - (minutosRestantes % 60)) / 60;
                    }


                    if (minutosRestantes <= 0 && horas <= 0 && minutosRestantes <= 0) {
                        List<String> lore = new ArrayList<String>();
                        lore.add(MethodsStatics.formater("&aItem disponivel"));
                        itemMeta.setLore(lore);
                    } else {
                        List<String> lore = new ArrayList<String>();
                        lore.add(MethodsStatics.formater("&cItem nao disponivel"));
                        lore.add(MethodsStatics.formater("&ADisponivel nos proximos"));
                        lore.add(MethodsStatics.formater("&6" + minutosRestantes / 1440 + " Dias " + horas + " Horas e " + minutosRestantes % 60 + " minutos"));
                        itemMeta.setLore(lore);
                    }



                } else {
                    List<String> lore = new ArrayList<String>();
                    lore.add(MethodsStatics.formater("&aItem disponivel"));
                    itemMeta.setLore(lore);
                }

            if (!player.hasPermission(locationFile.getString("kit." + key + ".perm"))) {
                List<String> lore = new ArrayList<String>();
                lore.add(MethodsStatics.formater("&cVoce nao tem permissao para pegar esse kit!"));
                itemMeta.setLore(lore);
            }


                itemMeta.setDisplayName(MethodsStatics.formater(itemName));


                item.setItemMeta(itemMeta);
                inventory.setItem(locationFile.getInt("kit." + key + ".pos"), item);

        }
        player.openInventory(inventory);
    }
}