package fallcraftsystem.modules.tpa.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.tpa.commands.*;
import fallcraftsystem.modules.tpa.utils.TpaConfig;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadTpaModules {
    public LoadTpaModules(FallCraftSystem plugin) {
        try {
            TpaConfig.setupTpaConfig(plugin);
            new Tpa(plugin);
            new Tpevento(plugin);
            new Tpahere(plugin);
            new Tpaccept(plugin);
            new Tpdeny(plugin);
            new Tpatoggle(plugin);

            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aTpa load"));

        }catch (Exception exception) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cTpa not load"));
        }
    }
}
