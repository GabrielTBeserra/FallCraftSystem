package fallcraftsystem.modules.tpa.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class TpaConfig {
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;

    public TpaConfig(final FallCraftSystem main) {
        TpaConfig.plugin = main;
    }

    public static void setupTpaConfig(final FallCraftSystem main) {
        TpaConfig.plugin = main;
        if (!TpaConfig.plugin.getDataFolder().exists()) {
            TpaConfig.plugin.getDataFolder().mkdir();
        }

        TpaConfig.file = new File(TpaConfig.plugin.getDataFolder(), "tpa-config.yml");

        if (!TpaConfig.file.exists()) {
            try {
                TpaConfig.plugin.saveResource("tpa-config.yml", false);
            } catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("§cNão foi possível criar o arquivo reparar-config.yml!");
                localException1.printStackTrace();
            }
        }
        TpaConfig.userfile = YamlConfiguration.loadConfiguration(TpaConfig.file);
    }

    public static void reload() {
        TpaConfig.userfile = YamlConfiguration.loadConfiguration(TpaConfig.file);
    }

    public static FileConfiguration getTpaFile() {
        return TpaConfig.userfile;
    }

    public static void save() {
        try {
            TpaConfig.userfile.save(TpaConfig.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
