package fallcraftsystem.modules.blockcommands.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.blockcommands.listener.BlockCommandListener;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.PluginInfo;

public class LoadBlockCommandModule {
    public LoadBlockCommandModule(FallCraftSystem pl) {
        try {
            BlocksCommands.blockLoad();
            new BlockCommandListener(pl);
            pl.getServer().getConsoleSender().sendMessage(Ultilities.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &aBlockCommands load"));
        } catch (Exception e) {
            pl.getServer().getConsoleSender().sendMessage(Ultilities.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &cBlockCommands not load"));
        }
    }
}
