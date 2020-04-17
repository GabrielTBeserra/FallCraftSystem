package fallcraftsystem.modules.npc.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.npc.listener.NpcsEvents;

public class LoadNpcModule {
    private LoadNPC loadNPC;

    public LoadNPC getLoadNPC() {
        return loadNPC;
    }

    public void setLoadNPC(LoadNPC loadNPC) {
        this.loadNPC = loadNPC;
    }

    public LoadNpcModule(FallCraftSystem plugin){
        new NpcsEvents(plugin);
        loadNPC = new LoadNPC();
    }


}
