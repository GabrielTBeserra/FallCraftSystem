package fallcraftsystem.core;

import fallcraftsystem.modules.blockcommands.utils.OptionBlockFile;
import fallcraftsystem.modules.chest.utils.ChestsList;
import fallcraftsystem.modules.essentials.spawn.utils.SpawnFile;
import fallcraftsystem.modules.essentials.warp.utils.WarpFile;
import fallcraftsystem.modules.home.utils.HomeConfig;
import fallcraftsystem.modules.home.utils.HomeDB;
import fallcraftsystem.modules.kits.utils.KitConfig;
import fallcraftsystem.modules.kits.utils.KitDbConfig;
import fallcraftsystem.modules.npc.utils.NpcFile;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadFall implements CommandExecutor {
    public FallCraftSystem plugin;

    public ReloadFall(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("reloadfall").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        SpawnFile.reload();
        WarpFile.reload();
        HomeDB.reload();
        HomeConfig.reload();
        KitDbConfig.reload();
        KitConfig.reload();
        NpcFile.reload();
        WarpFile.reload();
        OptionBlockFile.reload();
        ChestsList.reload();

        commandSender.sendMessage(Ultilities.formater("&aArquivos de configuracao recarregado!"));


        return true;
    }
}
