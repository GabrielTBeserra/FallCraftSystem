package fallcraftsystem.core;

import fallcraftsystem.modules.blockcommands.core.LoadBlockCommandModule;
import fallcraftsystem.modules.essentials.commands.LoadEssentialCommandsModule;
import fallcraftsystem.modules.essentials.spawn.LoadEssentialSpawnModule;
import fallcraftsystem.modules.essentials.warp.LoadEssentialWarpModule;
import fallcraftsystem.modules.heathbar.core.ModuleHearthBar;
import fallcraftsystem.modules.scoreboard.core.LoadScoreboard;
import fallcraftsystem.utils.generalevents.GeneralEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class FallCraftSystem extends JavaPlugin {

    @Override
    public void onEnable() {
        loadModules();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadModules() {
        new LoadEssentialWarpModule(this);
        new LoadEssentialSpawnModule(this);
        new LoadEssentialCommandsModule(this);
        new ModuleHearthBar(this);
        new LoadScoreboard(this);
        new GeneralEvents(this);
        new LoadBlockCommandModule(this);
        new Save(this);
        new Recovery(this);

    }
}
