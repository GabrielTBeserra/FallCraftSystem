package fallcraftsystem.modules.blocks.utils;

import org.bukkit.Material;

public class Utilities {
    public static boolean isIngot(Material type) {
        return type.equals(Material.COAL)
                || (type.equals(Material.IRON_INGOT))
                || (type.equals(Material.GOLD_INGOT))
                || (type.equals(Material.REDSTONE))
                || (type.equals(Material.DIAMOND))
                || (type.equals(Material.EMERALD));
    }

    public static boolean isSnow(Material type) {
        return type.equals(Material.SNOW_BALL);
    }
}
