package fallcraftsystem.modules.essentials.spawn;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.spawn.commands.Lobby;
import fallcraftsystem.modules.essentials.spawn.commands.SetLobby;
import fallcraftsystem.modules.essentials.spawn.utils.SpawnFile;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.ServerUtils;

public class LoadEssentialSpawnModule {
    public LoadEssentialSpawnModule(FallCraftSystem fallCraftSystem) {
        try {
            SpawnFile.setupSpawnFile(fallCraftSystem);
            new Lobby(fallCraftSystem);
            new SetLobby(fallCraftSystem);

            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aSpawn load"));
        } catch (Exception e) {
            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aSpawn not load"));
        }

    }
}
