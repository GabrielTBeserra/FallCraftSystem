package fallcraftsystem.utils;

import fallcraftsystem.entities.GamePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PluginInfo {
    public static String PLUGIN_NAME;
    public static int MENU_SIZE;
    public static String SERVER_NAME;
    public static Map<Player, GamePlayer> players;

    static {
        PluginInfo.PLUGIN_NAME = "&FFallPL &9&l>>&f ";
        PluginInfo.MENU_SIZE = 54;
        PluginInfo.SERVER_NAME = "&FFall&bCraft &9&l>>&f ";
        PluginInfo.players = new HashMap<Player, GamePlayer>();
    }

}
