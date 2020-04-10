package fallcraftsystem.modules.blockcommands.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.blockcommands.listener.BlockCommandListener;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;

public class LoadBlockCommandModule {
    public LoadBlockCommandModule(FallCraftSystem pl) {
        try {
            BlocksCommands.blockLoad();
            new BlockCommandListener(pl);
            pl.getServer().getConsoleSender().sendMessage(MethodsStatics.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &aBlockCommands load"));
        } catch (Exception e) {
            pl.getServer().getConsoleSender().sendMessage(MethodsStatics.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &cBlockCommands not load"));
        }
    }
}
