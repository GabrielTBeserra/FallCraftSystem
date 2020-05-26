package fallcraftsystem.modules.ajudastaff.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.ajudastaff.commands.AjudaStaff;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;

public class LoadAjudaStaffModules {
    public LoadAjudaStaffModules(FallCraftSystem plugin) {
        try {
            new AjudaStaff(plugin);

            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aAjudaStaff load"));
        } catch (Exception exception) {
            plugin.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cAjudaStaff not load"));
        }
    }
}
