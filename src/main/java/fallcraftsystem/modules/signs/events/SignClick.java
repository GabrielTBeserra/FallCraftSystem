package fallcraftsystem.modules.signs.events;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.signs.utils.RepairSign;
import fallcraftsystem.modules.signs.utils.Utilities;
import fallcraftsystem.utils.dependencies.VaultEconomy;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class SignClick implements Listener {
    public FallCraftSystem plugin;

    public SignClick(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                Player player = event.getPlayer();
                RepairSign repairSign = new RepairSign(sign);

                if (!repairSign.isRepair()) return;

                float balance = (float) VaultEconomy.getVault().getBalance(player);
                float price = repairSign.getPrice();

                player.sendMessage(String.valueOf(balance));
                player.sendMessage(String.valueOf(price));

                if (balance < price) {
                    player.sendMessage("§cVocê não possui dinheiro o suficiente.");
                    return;
                }

                VaultEconomy.getVault().withdrawPlayer(player, price);

                Utilities.repair(player, repairSign.isAllBool());

                player.sendMessage("§aRemovidos $" + price + " da sua conta.");
                event.setCancelled(true);
            }

        }
    }
}
