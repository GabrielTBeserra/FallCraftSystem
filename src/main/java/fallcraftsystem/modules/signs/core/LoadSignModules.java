package fallcraftsystem.modules.signs.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.signs.commands.SignEditor;
import fallcraftsystem.modules.signs.events.SignClick;
import fallcraftsystem.modules.signs.events.SignCreate;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadSignModules {
    public LoadSignModules(FallCraftSystem plugin) {
        try{
            new SignEditor(plugin);
            new SignCreate(plugin);
            new SignClick(plugin);
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aSigns load"));

        } catch (Exception exception){

            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cSigns not load"));
        }
    }
}
