package fallcraftsystem.utils.dependencies;

import fallcraftsystem.core.FallCraftSystem;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getServer;

public class PEX {
    private static Permission perms;

    public static boolean setupPEX(final FallCraftSystem plugin) {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Permission getPEX() {
        return PEX.perms;
    }
}
