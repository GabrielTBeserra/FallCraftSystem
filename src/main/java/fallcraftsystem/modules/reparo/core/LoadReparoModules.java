package fallcraftsystem.modules.reparo.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.reparo.commands.Reparo;
import fallcraftsystem.modules.reparo.utils.ReparoConfig;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadReparoModules {
    public LoadReparoModules(FallCraftSystem plugin) {
        try {
            new Reparo(plugin);
            ReparoConfig.setupRepararConfig(plugin);

            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aReparo load"));
        } catch (Exception exception) {


            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cReparo not load"));
        }
    }
}
