package fallcraftsystem.modules.luz.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.luz.commands.Luz;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadLuzModules {
    public LoadLuzModules(FallCraftSystem plugin){
        try {
            new Luz(plugin);
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aLuz load"));

        } catch (Exception exception) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cLuz not load"));
        }
    }
}
