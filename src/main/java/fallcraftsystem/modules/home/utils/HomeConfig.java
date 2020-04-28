package fallcraftsystem.modules.home.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class HomeConfig {
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;

    public HomeConfig(final FallCraftSystem main) {
        HomeConfig.plugin = main;
    }

    public static void setupHomeConfig(final FallCraftSystem main) {
        HomeConfig.plugin = main;
        if (!HomeConfig.plugin.getDataFolder().exists()) {
            HomeConfig.plugin.getDataFolder().mkdir();
        }

        HomeConfig.file = new File(HomeConfig.plugin.getDataFolder(), "home-config.yml");

        if (!HomeConfig.file.exists()) {
            try {
                HomeConfig.plugin.saveResource("home-config.yml", false);
            } catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("\ufffdcN\ufffdo foi poss\ufffdvel criar o arquivo home-config.yml!");
                localException1.printStackTrace();
            }
        }
        HomeConfig.userfile = YamlConfiguration.loadConfiguration(HomeConfig.file);
    }

    public static void reload() {
        HomeConfig.userfile = YamlConfiguration.loadConfiguration(HomeConfig.file);
    }

    public static FileConfiguration getHomeFile() {
        return HomeConfig.userfile;
    }

    public static void save() {
        try {
            HomeConfig.userfile.save(HomeConfig.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
