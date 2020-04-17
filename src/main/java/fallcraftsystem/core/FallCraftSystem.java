package fallcraftsystem.core;

import fallcraftsystem.modules.blockcommands.core.LoadBlockCommandModule;
import fallcraftsystem.modules.essentials.commands.LoadEssentialCommandsModule;
import fallcraftsystem.modules.essentials.spawn.LoadEssentialSpawnModule;
import fallcraftsystem.modules.essentials.spy.core.LoadSpyModule;
import fallcraftsystem.modules.essentials.warp.LoadEssentialWarpModule;
import fallcraftsystem.modules.heathbar.core.ModuleHearthBar;
import fallcraftsystem.modules.npc.core.LoadNpcModule;
import fallcraftsystem.modules.scoreboard.core.LoadScoreboard;
import fallcraftsystem.utils.MethodsStatics;
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
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&a======================================================="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&a=     ########### ########## #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&a=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&a=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&a=     ########    ########## #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&a=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&a=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&a=     #           #        # ########## ##########    ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&a======================================================="));

        loadModules();
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&c======================================================="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&c=     ########### ########## #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&c=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&c=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&c=     ########    ########## #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&c=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&c=     #           #        # #          #             ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&c=     #           #        # ########## ##########    ="));
        Bukkit.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&c======================================================="));
    }

    private void loadModules() {
        ChatVault.setupChat(this);
        PEX.setupPEX(this);
        VaultEconomy.setupEconomy(this);
        new LoadNpcModule(this);
        new PluginInfo();
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
