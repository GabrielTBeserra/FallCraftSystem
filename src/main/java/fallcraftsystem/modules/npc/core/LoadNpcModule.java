package fallcraftsystem.modules.npc.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.npc.listener.NpcsEvents;
import fallcraftsystem.modules.npc.utils.NpcFile;

public class LoadNpcModule {
    private LoadNPC loadNPC;

    public LoadNpcModule(FallCraftSystem plugin) {
        NpcFile.setupNpcFile(plugin);

        new NpcsEvents(plugin);
        loadNPC = new LoadNPC();
    }

    public LoadNPC getLoadNPC() {
        return loadNPC;
    }

    public void setLoadNPC(LoadNPC loadNPC) {
        this.loadNPC = loadNPC;
    }


}
