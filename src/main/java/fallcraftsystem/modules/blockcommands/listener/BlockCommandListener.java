package fallcraftsystem.modules.blockcommands.listener;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.blockcommands.core.BlocksCommands;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.ServerUtils;
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
        final String[] message = event.getMessage().toLowerCase().split(" ");
        if (BlocksCommands.blockedCommands.contains(message[0])) {
            event.getPlayer().sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cYou don`t have permission for this!"));
            event.setCancelled(true);
        }
    }
}
