package fallcraftsystem.modules.blocks.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.blocks.commands.Blocks;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadBlockModules {
    public LoadBlockModules(FallCraftSystem plugin) {
        try {
            new Blocks(plugin);

            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aBloco load"));
        } catch (Exception exception) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cBloco not load"));
        }
    }
}
