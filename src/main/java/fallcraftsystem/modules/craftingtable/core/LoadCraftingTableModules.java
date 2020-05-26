package fallcraftsystem.modules.craftingtable.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.craftingtable.command.CraftingTable;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadCraftingTableModules {
    public LoadCraftingTableModules(FallCraftSystem plugin) {
        try{
            new CraftingTable(plugin);

            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aCrafting Table load"));
        } catch (Exception exception) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cCrafting Table not load"));
        }
    }
}
