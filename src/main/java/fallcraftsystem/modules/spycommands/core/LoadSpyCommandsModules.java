package fallcraftsystem.modules.spycommands.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.spycommands.commands.SpyC;
import fallcraftsystem.modules.spycommands.events.ChatEvent;
import fallcraftsystem.modules.spycommands.events.CommandEvent;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadSpyCommandsModules {
    public LoadSpyCommandsModules(FallCraftSystem plugin){
        try {
            new SpyC(plugin);
            new ChatEvent(plugin);
            new CommandEvent(plugin);

            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aSpyCommands load"));
        } catch (Exception exception) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cScoreboard not load"));
        }
    }
}
