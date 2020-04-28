package fallcraftsystem.modules.essentials.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.PluginInfo;

public class LoadEssentialCommandsModule {
    public LoadEssentialCommandsModule(FallCraftSystem fallCraftSystem) {
        try {
           new Vanish(fallCraftSystem);
           new Fly(fallCraftSystem);
           new Gm(fallCraftSystem);
           new Tp(fallCraftSystem);
           new Tpall(fallCraftSystem);
           new Reloadall(fallCraftSystem);

            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &aCommands load"));
        } catch (Exception e) {
            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &aCommands not load"));
        }

    }
}
