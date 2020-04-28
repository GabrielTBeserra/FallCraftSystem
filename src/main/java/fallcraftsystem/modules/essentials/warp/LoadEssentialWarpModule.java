package fallcraftsystem.modules.essentials.warp;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.warp.commands.*;
import fallcraftsystem.modules.essentials.warp.utils.WarpFile;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.PluginInfo;

public class LoadEssentialWarpModule {
    public LoadEssentialWarpModule(FallCraftSystem fallCraftSystem) {
        try {
            WarpFile.setupWarpFile(fallCraftSystem);
            new DelWarp(fallCraftSystem);
            new SetWarp(fallCraftSystem);
            new Warp(fallCraftSystem);
            new WarpList(fallCraftSystem);

            fallCraftSystem.getServer().getPluginManager().registerEvents(new ClickItemTeleport(), fallCraftSystem);
            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &aWarp load"));
        } catch (Exception e) {
            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &cWarp not load"));
        }

    }
}
