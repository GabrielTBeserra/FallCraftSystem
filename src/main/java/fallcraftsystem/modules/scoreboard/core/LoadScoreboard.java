package fallcraftsystem.modules.scoreboard.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.kits.utils.KitConfig;
import fallcraftsystem.modules.scoreboard.listener.GameScoreboard;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;

public class LoadScoreboard {
    public LoadScoreboard(FallCraftSystem plugin) {
        try {

            new GameScoreboard(plugin);

            plugin.getServer().getConsoleSender().sendMessage(MethodsStatics.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &aScoreboard load"));
        } catch (Exception e) {
            plugin.getServer().getConsoleSender().sendMessage(MethodsStatics.formater(PluginInfo.PLUGIN_NAME + "&cModule &f>> &cScoreboard not load"));
        }
    }
}
