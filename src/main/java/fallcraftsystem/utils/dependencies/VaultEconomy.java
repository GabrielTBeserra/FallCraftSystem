package fallcraftsystem.utils.dependencies;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.MethodsStatics;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultEconomy {
    private static Economy economy;

    public static boolean setupEconomy(final FallCraftSystem plugin) {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            plugin.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&fFast&bMC&f-&9Player: &cVault is requeried!"));
            return false;
        }
        plugin.getServer().getConsoleSender().sendMessage(MethodsStatics.formater("&fFast&bMC&f-&9Player: &aVault is loaded!"));
        final RegisteredServiceProvider<Economy> rsp = (RegisteredServiceProvider<Economy>) plugin.getServer().getServicesManager().getRegistration((Class) Economy.class);
        if (rsp == null) {
            return false;
        }
        VaultEconomy.economy = rsp.getProvider();
        return VaultEconomy.economy != null;
    }

    public static Economy getVault() {
        return VaultEconomy.economy;
    }
}