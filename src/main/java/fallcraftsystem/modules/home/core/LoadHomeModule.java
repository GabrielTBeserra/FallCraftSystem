package fallcraftsystem.modules.home.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.home.commands.DelHome;
import fallcraftsystem.modules.home.commands.Home;
import fallcraftsystem.modules.home.commands.HomeList;
import fallcraftsystem.modules.home.commands.SetHome;
import fallcraftsystem.modules.home.utils.HomeConfig;
import fallcraftsystem.modules.home.utils.HomeDB;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.ServerUtils;

public class LoadHomeModule {
    public LoadHomeModule(FallCraftSystem pl) {
        try {
            HomeDB.setupHomeConfig(pl);
            HomeConfig.setupHomeConfig(pl);
            new Home(pl);
            new SetHome(pl);
            new HomeList(pl);
            new DelHome(pl);

            pl.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &aHome load"));
        } catch (Exception e) {
            pl.getServer().getConsoleSender().sendMessage(Ultilities.formater(ServerUtils.PLUGIN_NAME + "&cModule &f>> &cHome not load"));
        }
    }
}
