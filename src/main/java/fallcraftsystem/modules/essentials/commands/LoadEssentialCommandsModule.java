package fallcraftsystem.modules.essentials.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.commands.admin.*;
import fallcraftsystem.modules.essentials.commands.player.Discord;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadEssentialCommandsModule {
    public LoadEssentialCommandsModule(FallCraftSystem fallCraftSystem) {
        try {
            new Vanish(fallCraftSystem);
            new Fly(fallCraftSystem);
            new Gm(fallCraftSystem);
            new Tp(fallCraftSystem);
            new Tphere(fallCraftSystem);
            new Tpall(fallCraftSystem);
            new Reloadall(fallCraftSystem);
            new Discord(fallCraftSystem);
            new Inv(fallCraftSystem);
            new AvisoComm(fallCraftSystem);

            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aCommands load"));
        } catch (Exception e) {
            fallCraftSystem.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aCommands not load"));
        }

    }
}
