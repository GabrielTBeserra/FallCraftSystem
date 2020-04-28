package fallcraftsystem.core;

import fallcraftsystem.modules.blockcommands.core.LoadBlockCommandModule;
import fallcraftsystem.modules.essentials.commands.LoadEssentialCommandsModule;
import fallcraftsystem.modules.essentials.spawn.LoadEssentialSpawnModule;
import fallcraftsystem.modules.essentials.spy.core.LoadSpyModule;
import fallcraftsystem.modules.essentials.warp.LoadEssentialWarpModule;
import fallcraftsystem.modules.heathbar.core.ModuleHearthBar;
import fallcraftsystem.modules.home.core.LoadHomeModule;
import fallcraftsystem.modules.kits.core.LoadKitModules;
import fallcraftsystem.modules.npc.core.LoadNpcModule;
import fallcraftsystem.modules.scoreboard.core.LoadScoreboard;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.PluginInfo;
import fallcraftsystem.utils.dependencies.ChatVault;
import fallcraftsystem.utils.dependencies.PEX;
import fallcraftsystem.utils.dependencies.VaultEconomy;
import fallcraftsystem.utils.generalevents.GeneralEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FallCraftSystem extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a======================================================="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     ########### ########## #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     ########    ########## #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a=     #           #        # ########## ##########    ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&a======================================================="));

        loadModules();
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c======================================================="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     ########### ########## #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     ########    ########## #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c=     #           #        # ########## ##########    ="));
        Bukkit.getServer().getConsoleSender().sendMessage(Ultilities.formater("&c======================================================="));
    }

    private void loadModules() {
        ChatVault.setupChat(this);
        PEX.setupPEX(this);
        VaultEconomy.setupEconomy(this);
        new LoadNpcModule(this);
        new PluginInfo();
        new LoadKitModules(this);
        new LoadHomeModule(this);
        new LoadEssentialWarpModule(this);
        new LoadEssentialSpawnModule(this);
        new LoadEssentialCommandsModule(this);
        new ModuleHearthBar(this);
        new LoadSpyModule(this);
        new LoadScoreboard(this);
        new GeneralEvents(this);
        new LoadBlockCommandModule(this);


    }
}
