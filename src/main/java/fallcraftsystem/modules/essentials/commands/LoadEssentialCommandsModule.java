package fallcraftsystem.modules.essentials.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.spawn.commands.Lobby;
import fallcraftsystem.modules.essentials.spawn.commands.SetLobby;
import fallcraftsystem.modules.essentials.spawn.utils.SpawnFile;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;

public class LoadEssentialCommandsModule {
    public LoadEssentialCommandsModule(FallCraftSystem fallCraftSystem) {
        try {
           new Vanish(fallCraftSystem);
           new Fly(fallCraftSystem);
           new Gm(fallCraftSystem);
           new Tp(fallCraftSystem);

            fallCraftSystem.getServer().getConsoleSender().sendMessage(MethodsStatics.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &aSpawn load"));
        } catch (Exception e) {
            fallCraftSystem.getServer().getConsoleSender().sendMessage(MethodsStatics.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &aSpawn not load"));
        }

    }
}
