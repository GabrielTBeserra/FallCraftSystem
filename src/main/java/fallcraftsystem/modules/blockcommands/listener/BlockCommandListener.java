package fallcraftsystem.modules.blockcommands.listener;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.blockcommands.core.BlocksCommands;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockCommandListener implements Listener {
    public FallCraftSystem plugin;

    public BlockCommandListener(FallCraftSystem plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this , plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {
        final String[] args = event.getMessage().toLowerCase().split(" ");
        final String[] message = event.getMessage().toLowerCase().split(" ");
        if (BlocksCommands.blockedCommands.contains(message[0])) {
            event.getPlayer().sendMessage(MethodsStatics.formater(PluginInfo.SERVER_NAME + "&cYou don`t have permission for this!"));
            event.setCancelled(true);
        }
    }
}
