package fallcraftsystem.modules.kits.listeners;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.kits.commands.CreateKit;
import fallcraftsystem.modules.kits.core.KitInv;
import fallcraftsystem.modules.kits.utils.KitConfig;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.SaveInventory;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class CloseAndCreateKit implements Listener {
    public FallCraftSystem plugin;

    public CloseAndCreateKit(FallCraftSystem pl) {
        this.plugin = pl;
        plugin.getServer().getPluginManager().registerEvents(this, pl);
    }

    @EventHandler
    public void oncloseCreate(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (!event.getInventory().getName().contains("KIT")) {
            return;
        }
        String[] names = event.getInventory().getName().split("\\.");
        String name = names[1];

        KitInv kit = CreateKit.listInvsKits.get(name);
        Inventory inventory = event.getInventory();


        String invent = SaveInventory.InventoryToString(inventory);
        SaveInventory.save(plugin, invent, kit.getKitName());


        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".name", kit.getKitName());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".icon", kit.getIcon());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".type", kit.getType());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".perm", kit.getKitPerm());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".effect", kit.isEnchant());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".pos", kit.getPos());
        KitConfig.getKitFIle().set("kit." + kit.getKitName() + ".time", kit.getTime());
        KitConfig.save();

        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aKit &6" + StringUtils.capitalize(kit.getKitName()) + " &acriado com sucesso!"));

    }

}
