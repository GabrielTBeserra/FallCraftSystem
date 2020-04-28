package fallcraftsystem.modules.essentials.spy.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.essentials.spy.command.SpyCommand;
import fallcraftsystem.modules.essentials.spy.listener.SpyChat;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.PluginInfo;

public class LoadSpyModule {
    public LoadSpyModule(FallCraftSystem plugin) {
        try{
        new SpyChat(plugin);
        new SpyCommand(plugin);
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &aSpy load"));
    } catch (Exception e) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &cSpy not load"));
    }
    }
}
