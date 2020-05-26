package fallcraftsystem.modules.item.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.item.commands.*;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadItemModules {
    public LoadItemModules(FallCraftSystem plugin) {
        try {
            new ItemAjuda(plugin);
            new ItemName(plugin);
            new ItemDesc(plugin);
            new ItemEnchant(plugin);
//            new SetDurability(plugin); -> Não está pronto e não pretendo usar :/

            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aItem load"));
        } catch (Exception exception) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cItem not load"));
        }
    }
}
