package fallcraftsystem.core;

import fallcraftsystem.modules.ajudastaff.core.LoadAjudaStaffModules;
import fallcraftsystem.modules.automessages.core.AutoMessage;
import fallcraftsystem.modules.automsg.core.LoadAutoMSGModules;
import fallcraftsystem.modules.blockcommands.core.LoadBlockCommandModule;
import fallcraftsystem.modules.blockcommands.utils.OptionBlockFile;
import fallcraftsystem.modules.blocks.core.LoadBlockModules;
import fallcraftsystem.modules.chest.core.LoadChestModules;
import fallcraftsystem.modules.coin.core.LoadCoinModule;
import fallcraftsystem.modules.craftingtable.core.LoadCraftingTableModules;
import fallcraftsystem.modules.essentials.commands.LoadEssentialCommandsModule;
import fallcraftsystem.modules.essentials.spawn.LoadEssentialSpawnModule;
import fallcraftsystem.modules.essentials.spy.core.LoadSpyModule;
import fallcraftsystem.modules.essentials.warp.LoadEssentialWarpModule;
import fallcraftsystem.modules.heathbar.core.ModuleHearthBar;
import fallcraftsystem.modules.home.core.LoadHomeModule;
import fallcraftsystem.modules.invsee.core.LoadInvseeModules;
import fallcraftsystem.modules.kits.core.LoadKitModules;
import fallcraftsystem.modules.luz.core.LoadLuzModules;
import fallcraftsystem.modules.npc.core.LoadNpcModule;
import fallcraftsystem.modules.reparo.core.LoadReparoModules;
import fallcraftsystem.modules.scoreboard.core.LoadScoreboard;
import fallcraftsystem.modules.scoreboardoff.core.LoadScoreboardOffModules;
import fallcraftsystem.modules.tpa.core.LoadTpaModules;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.dependencies.ChatVault;
import fallcraftsystem.utils.dependencies.PEX;
import fallcraftsystem.utils.dependencies.VaultEconomy;
import fallcraftsystem.utils.generalevents.GeneralEvents;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class FallCraftSystem extends JavaPlugin {
    public static FallCraftSystem plugin;

    @Override
    public void onEnable() {
        plugin = this;

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

        HandlerList.unregisterAll(this);
    }

    private void loadModules() {
        Ultilities.teleportTimer();
        ChatVault.setupChat(this);
        PEX.setupPEX(this);
        VaultEconomy.setupEconomy(this);
        OptionBlockFile.setupOptionBlock(this);
        new LoadNpcModule(this);
        new ReloadFall(this);
        new LoadCoinModule(this);
        new ServerUtils();
        new AutoMessage(this);
        new LoadKitModules(this);
        new LoadHomeModule(this);
        new LoadEssentialWarpModule(this);
        new LoadEssentialSpawnModule(this);
        new LoadEssentialCommandsModule(this);
        new ModuleHearthBar(this);
        new LoadSpyModule(this);
        new LoadScoreboard(this);
        new GeneralEvents(this);
        new LoadAjudaStaffModules(this);
        new LoadBlockCommandModule(this);
        new LoadLuzModules(this);
        new LoadBlockModules(this);
        new LoadCraftingTableModules(this);
        new LoadChestModules(this);
        new LoadScoreboardOffModules(this);
        new LoadInvseeModules(this);
        new LoadReparoModules(this);
        new LoadTpaModules(this);
        new LoadAutoMSGModules(this);
    }
}
