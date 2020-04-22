package fallcraftsystem.modules.npc.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class NpcFile {
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;

    public NpcFile(final FallCraftSystem main) {
        NpcFile.plugin = main;
    }

    public static void setupNpcFile(final FallCraftSystem main) {
        NpcFile.plugin = main;
        if (!NpcFile.plugin.getDataFolder().exists()) {
            NpcFile.plugin.getDataFolder().mkdir();
        }

        NpcFile.file = new File(NpcFile.plugin.getDataFolder() + "/npc/", "npc.yml");

        if (!NpcFile.file.exists()) {
            try {
                NpcFile.plugin.saveResource("npc\\npc.yml", false);
            } catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("\ufffdcN\ufffdo foi poss\ufffdvel criar o arquivo npc.yml!");
                localException1.printStackTrace();
            }
        }
        NpcFile.userfile = YamlConfiguration.loadConfiguration(NpcFile.file);
    }

    public static void reload() {
        NpcFile.userfile = YamlConfiguration.loadConfiguration(NpcFile.file);
    }

    public static FileConfiguration getNpcFile() {
        return NpcFile.userfile;
    }

    public static void save() {
        try {
            NpcFile.userfile.save(NpcFile.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
