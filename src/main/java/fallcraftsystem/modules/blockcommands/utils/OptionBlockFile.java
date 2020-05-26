package fallcraftsystem.modules.blockcommands.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class OptionBlockFile {
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;

    public OptionBlockFile(final FallCraftSystem main) {
        OptionBlockFile.plugin = main;
    }

    public static void setupOptionBlock(final FallCraftSystem main) {
        OptionBlockFile.plugin = main;
        if (!OptionBlockFile.plugin.getDataFolder().exists()) {
            OptionBlockFile.plugin.getDataFolder().mkdir();
        }

        OptionBlockFile.file = new File(OptionBlockFile.plugin.getDataFolder(), "option.yml");

        if (!OptionBlockFile.file.exists()) {
            try {
                OptionBlockFile.plugin.saveResource("option.yml", false);
            } catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("\ufffdcN\ufffdo foi poss\ufffdvel criar o arquivo npc.yml!");
                localException1.printStackTrace();
            }
        }
        OptionBlockFile.userfile = YamlConfiguration.loadConfiguration(OptionBlockFile.file);
    }

    public static void reload() {
        OptionBlockFile.userfile = YamlConfiguration.loadConfiguration(OptionBlockFile.file);
    }

    public static FileConfiguration getOptionFile() {
        return OptionBlockFile.userfile;
    }

    public static void save() {
        try {
            OptionBlockFile.userfile.save(OptionBlockFile.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
