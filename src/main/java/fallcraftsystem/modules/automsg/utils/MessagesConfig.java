package fallcraftsystem.modules.automsg.utils;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessagesConfig {
    public static FallCraftSystem plugin;
    private static File file;
    private static FileConfiguration userfile;

    public MessagesConfig(final FallCraftSystem main) {
        MessagesConfig.plugin = main;
    }

    public static void setupMessagesConfig(final FallCraftSystem main) {
        MessagesConfig.plugin = main;
        if (!MessagesConfig.plugin.getDataFolder().exists()) {
            MessagesConfig.plugin.getDataFolder().mkdir();
        }

        MessagesConfig.file = new File(MessagesConfig.plugin.getDataFolder(), "auto-messages.yml");

        if (!MessagesConfig.file.exists()) {
            try {
                MessagesConfig.plugin.saveResource("auto-messages.yml", false);
            } catch (Exception localException1) {
                Bukkit.getConsoleSender().sendMessage("§cNão foi possível criar o arquivo auto-messages.yml!");
                localException1.printStackTrace();
            }
        }
        MessagesConfig.userfile = YamlConfiguration.loadConfiguration(MessagesConfig.file);
    }

    public static void reload() {
        MessagesConfig.userfile = YamlConfiguration.loadConfiguration(MessagesConfig.file);
    }

    public static FileConfiguration getMessagesFile() {
        return MessagesConfig.userfile;
    }

    public static void save() {
        try {
            MessagesConfig.userfile.save(MessagesConfig.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
