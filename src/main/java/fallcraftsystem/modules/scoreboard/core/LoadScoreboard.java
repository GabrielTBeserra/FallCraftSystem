package fallcraftsystem.modules.scoreboard.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.scoreboard.listener.GameScoreboard;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.ServerUtils;

public class LoadScoreboard {
    public LoadScoreboard(FallCraftSystem plugin) {
        try {

            new GameScoreboard(plugin);

            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aScoreboard load"));
        } catch (Exception e) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cScoreboard not load"));
        }
    }
}
