package fallcraftsystem.modules.home.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class HomeDB {
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;

    public HomeDB(final FallCraftSystem main) {
        HomeDB.plugin = main;
    }

    public static void setupHomeConfig(final FallCraftSystem main) {
        HomeDB.plugin = main;
        if (!HomeDB.plugin.getDataFolder().exists()) {
            HomeDB.plugin.getDataFolder().mkdir();
        }

        HomeDB.file = new File(HomeDB.plugin.getDataFolder(), "homes.yml");

        if (!HomeDB.file.exists()) {
            try {
                HomeDB.plugin.saveResource("homes.yml", false);
            } catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("\ufffdcN\ufffdo foi poss\ufffdvel criar o arquivo homes.yml!");
                localException1.printStackTrace();
            }
        }
        HomeDB.userfile = YamlConfiguration.loadConfiguration(HomeDB.file);
    }

    public static void reload() {
        HomeDB.userfile = YamlConfiguration.loadConfiguration(HomeDB.file);
    }

    public static FileConfiguration getHomeFile() {
        return HomeDB.userfile;
    }

    public static void save() {
        try {
            HomeDB.userfile.save(HomeDB.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
