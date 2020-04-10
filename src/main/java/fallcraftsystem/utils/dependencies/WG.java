package fallcraftsystem.utils.dependencies;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.plugin.Plugin;

public class WG {
    public static WorldGuardPlugin getWorldGuardPlugin(FallCraftSystem plugin) {
        Plugin wg = plugin.getServer().getPluginManager().getPlugin("WorldGuard");



        if (wg == null || !(wg instanceof WorldGuardPlugin)) {
            return null;
        }

        return (WorldGuardPlugin) wg;
    }
}
