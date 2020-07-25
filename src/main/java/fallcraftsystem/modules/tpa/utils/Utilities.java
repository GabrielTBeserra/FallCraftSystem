package fallcraftsystem.modules.tpa.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Utilities {
    private static HashMap<Player, Player> tpa = new HashMap<Player, Player>();

    public static HashMap<Player, Player> getTpa() {
        return tpa;
    }

    private static HashMap<Player, Player> tpaHere = new HashMap<Player, Player>();

    public static HashMap<Player, Player> getTpaHere() {
        return tpaHere;
    }
}
