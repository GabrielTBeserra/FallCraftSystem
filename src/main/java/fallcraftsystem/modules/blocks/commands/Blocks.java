package fallcraftsystem.modules.blocks.commands;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static fallcraftsystem.modules.blocks.utils.Utilities.*;

public class Blocks implements CommandExecutor {
    public FallCraftSystem plugin;

    public Blocks(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("blocks").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Commando apenas para players");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 0) return false;

        ItemStack[] contents = player.getInventory().getContents();

        int numDeCarv = 0;
        int numDeFer = 0;
        int numDeOur = 0;
        int numDeReds = 0;
        int numDeDiam = 0;
        int numDeEsm = 0;

        Material type;
        int index = 0;
        for (ItemStack content : contents) {
            if (content != null) {
                int amount = content.getAmount();
                type = content.getType();

                if (isIngot(type)){

                    if (type.equals(Material.COAL)) numDeCarv += amount;
                    if (type.equals(Material.IRON_INGOT)) numDeFer += amount;
                    if (type.equals(Material.GOLD_INGOT)) numDeOur += amount;
                    if (type.equals(Material.REDSTONE)) numDeReds += amount;
                    if (type.equals(Material.DIAMOND)) numDeDiam += amount;
                    if (type.equals(Material.EMERALD)) numDeEsm += amount;

                    player.getInventory().setItem(index, null);
                }
            }
            index++;
        }

        int blocosCarv = numDeCarv / 9;
        int blocosFer = numDeFer / 9;
        int blocosOur = numDeOur / 9;
        int blocosReds = numDeReds / 9;
        int blocosDiam = numDeDiam / 9;
        int blocosEsm = numDeEsm / 9;

        ItemStack blocosDeCarv = new ItemStack(Material.COAL_BLOCK, blocosCarv);
        ItemStack blocosDeFer = new ItemStack(Material.IRON_BLOCK, blocosFer);
        ItemStack blocosDeOur = new ItemStack(Material.GOLD_BLOCK, blocosOur);
        ItemStack blocosDeReds = new ItemStack(Material.REDSTONE_BLOCK, blocosReds);
        ItemStack blocosDeDiam = new ItemStack(Material.DIAMOND_BLOCK, blocosDiam);
        ItemStack blocosDeEsm = new ItemStack(Material.EMERALD_BLOCK, blocosEsm);

        if (blocosCarv != 0) player.getInventory().addItem(blocosDeCarv);
        if (blocosFer != 0) player.getInventory().addItem(blocosDeFer);
        if (blocosOur != 0) player.getInventory().addItem(blocosDeOur);
        if (blocosReds != 0) player.getInventory().addItem(blocosDeReds);
        if (blocosDiam != 0) player.getInventory().addItem(blocosDeDiam);
        if (blocosEsm != 0) player.getInventory().addItem(blocosDeEsm);

        numDeCarv = numDeCarv % 9;
        numDeFer = numDeFer % 9;
        numDeOur = numDeOur % 9;
        numDeReds = numDeReds % 9;
        numDeDiam = numDeDiam % 9;
        numDeEsm = numDeEsm % 9;

        ItemStack carv = new ItemStack(Material.COAL, numDeCarv);
        ItemStack fer = new ItemStack(Material.IRON_INGOT, numDeFer);
        ItemStack our = new ItemStack(Material.GOLD_INGOT, numDeOur);
        ItemStack reds = new ItemStack(Material.REDSTONE, numDeReds);
        ItemStack diam = new ItemStack(Material.DIAMOND, numDeDiam);
        ItemStack esm = new ItemStack(Material.EMERALD, numDeEsm);

        if (numDeCarv != 0) player.getInventory().addItem(carv);
        if (numDeFer != 0) player.getInventory().addItem(fer);
        if (numDeOur != 0) player.getInventory().addItem(our);
        if (numDeReds != 0) player.getInventory().addItem(reds);
        if (numDeDiam != 0) player.getInventory().addItem(diam);
        if (numDeEsm != 0) player.getInventory().addItem(esm);

        player.sendMessage("§aMinérios compactados! ");
        return true;
    }
}
