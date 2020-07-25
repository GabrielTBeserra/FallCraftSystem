package fallcraftsystem.modules.reparo.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ReparoConfig {
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;

    public ReparoConfig(final FallCraftSystem main) {
        ReparoConfig.plugin = main;
    }

    public static void setupRepararConfig(final FallCraftSystem main) {
        ReparoConfig.plugin = main;
        if (!ReparoConfig.plugin.getDataFolder().exists()) {
            ReparoConfig.plugin.getDataFolder().mkdir();
        }

        ReparoConfig.file = new File(ReparoConfig.plugin.getDataFolder(), "reparar-config.yml");

        if (!ReparoConfig.file.exists()) {
            try {
                ReparoConfig.plugin.saveResource("reparar-config.yml", false);
            } catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("§cNão foi possível criar o arquivo reparar-config.yml!");
                localException1.printStackTrace();
            }
        }
        ReparoConfig.userfile = YamlConfiguration.loadConfiguration(ReparoConfig.file);
    }

    public static void reload() {
        ReparoConfig.userfile = YamlConfiguration.loadConfiguration(ReparoConfig.file);
    }

    public static FileConfiguration getRepararFile() {
        return ReparoConfig.userfile;
    }

    public static void save() {
        try {
            ReparoConfig.userfile.save(ReparoConfig.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
