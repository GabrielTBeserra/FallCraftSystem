package fallcraftsystem.modules.kits.listeners;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.kits.utils.KitConfig;
import fallcraftsystem.modules.kits.utils.KitDbConfig;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.SaveInventory;
import fallcraftsystem.utils.TimeCalculator;
import fallcraftsystem.utils.Ultilities;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class ClickAndGetItem implements Listener {
    public FallCraftSystem plugin;

    public ClickAndGetItem(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void selectItem(InventoryClickEvent event) {
        // Verifica se o item clicado e ar ou vazio
        try {
            if (event.getCurrentItem().getType().equals(Material.AIR)) {
                return;
            }
        } catch (Exception e) {
            return;
        }
        // Caso o inventario tenhoa outro nome , nao continua a execucao
        if (!ChatColor.stripColor(event.getView().getTitle()).equals("Kits")) {
            return;
        }
        // Pega o nome do kit de acordo com o padrao KIT.<name>
        String kit = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase().trim();

        event.setCancelled(true);

        if (kit.equals("")) {
            return;
        }
        // Pega o nome do kit no arquivo de configuracoes
        String kitName = KitConfig.getKitFIle().getString("kit." + kit + ".name");
        // Recupera o inventario serializado
        String invString = SaveInventory.recovery(plugin, kitName);
        // Converte o inventario serializado em inventario objeto
        Inventory inventory = SaveInventory.StringToInventory(invString);
        // Pega qual player que esta clicando no intenrio
        Player player = (Player) event.getWhoClicked();


        if (KitDbConfig.getDBKItFile().contains(player.getUniqueId() + "." + kit)) {
            try {
                String hora = KitDbConfig.getDBKItFile().getString(player.getUniqueId() + "." + kit + ".time");
                Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hora);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Date date = new Date();

                int difEmMin = (int) TimeCalculator.diferencaEmMinutos(date1, date);
                int horaTotal = KitConfig.getKitFIle().getInt("kit." + kit + ".time");
                int minutosTotal = horaTotal * 60;
                int minutosRestantes = minutosTotal - difEmMin;

                int horas = minutosRestantes / 60;
                if (minutosRestantes / 60 > 24) {
                    horas = (((horaTotal - (horaTotal - (minutosRestantes / 1440)))) * 60 - (minutosRestantes % 60)) / 60;
                }

                Duration duracao = Duration.ofMinutes(minutosRestantes);

                long diasRestantesFinal = duracao.toDays();
                long horasRestantesFianl = duracao.toHours() % 24;
                long minutosRestantesFinal = duracao.toMinutes() % 60;

                if (!(player.isOp() || player.hasPermission("fallcraft.kit.bypass"))) {
                    if (diasRestantesFinal > 0 || horasRestantesFianl > 0 || minutosRestantesFinal > 0) {
                        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVoce ja pegou esse item!"));
                        event.setCancelled(true);
                        return;
                    }
                }

                //int dia = (int) TimeCalculator.diferencaEmDias(date1, date);
                //int horas = (int) TimeCalculator.diferencaEmHoras(date1, date);
                //int minutoss = (int) TimeCalculator.diferencaEmMinutos(date1, date);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (!player.hasPermission(KitConfig.getKitFIle().getString("kit." + kit + ".perm"))) {
            player.sendMessage(Ultilities.formater("&cVoce nao tem permissao para pegar esse kit!"));
            event.setCancelled(true);
            return;
        }

        event.setCancelled(true);


        for (ItemStack content : inventory.getContents()) {
            if (content != null) {
                player.getInventory().addItem(content);
            }
        }


        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aVoce pegou o kit &6" + StringUtils.capitalize(kitName)));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();

        String frmtdDate = dateFormat.format(date);


        KitDbConfig.getDBKItFile().set(player.getUniqueId() + "." + kitName + ".time", frmtdDate);
        KitDbConfig.save();

        player.closeInventory();
    }

}
