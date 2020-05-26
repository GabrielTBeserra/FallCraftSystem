package fallcraftsystem.modules.signs.events;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.signs.utils.RepairSign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignCreate implements Listener {
    public FallCraftSystem plugin;

    public SignCreate(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSignCreate(SignChangeEvent event) {

        Player player = event.getPlayer();
        String[] lines = event.getLines();
        int i = 0;
        for (String line : lines) {
            event.setLine(i, line.replace("&", "§"));
            i++;
        }

        RepairSign repairSign = new RepairSign(lines, player);

        if (!repairSign.isRepair()) return;

        if (!repairSign.playerHasPerm()) return;

        if (!repairSign.isValid()) {
            repairSign.invalidSign();
            return;
        }

        repairSign.signFormat();
        player.sendMessage("§aPlaca criada com sucesso!");
    }
}
