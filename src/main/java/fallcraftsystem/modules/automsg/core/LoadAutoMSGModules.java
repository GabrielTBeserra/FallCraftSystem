package fallcraftsystem.modules.automsg.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.automsg.commands.AutoMessage;
import fallcraftsystem.modules.automsg.events.PlayerJoin;
import fallcraftsystem.modules.automsg.utils.LoadMessages;
import fallcraftsystem.modules.automsg.utils.MessagesConfig;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadAutoMSGModules {
    public LoadAutoMSGModules(FallCraftSystem plugin) {
        try {
            MessagesConfig.setupMessagesConfig(plugin);
            new LoadMessages();
            new AutoMessage(plugin);
            new PlayerJoin(plugin);

            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aAutoMsg load"));
        } catch (Exception exception) {

            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cAutoMsg load"));
        }
    }
}
