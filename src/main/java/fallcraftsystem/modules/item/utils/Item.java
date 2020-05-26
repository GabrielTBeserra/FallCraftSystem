package fallcraftsystem.modules.item.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class Item {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public Item(ItemStack itemStack) {
        this.itemStack = itemStack;
        itemMeta = itemStack.getItemMeta();
    }

    public Item(Material m, int quantidade, short data) {
        itemStack = new ItemStack(m, quantidade, data);
        itemMeta = itemStack.getItemMeta();
    }

    public Item setName(String nome) {
        itemMeta.setDisplayName(nome.replace("&", "§"));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public Item setLore(List<String> list) {
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public boolean isAir(){ return itemStack.getType().equals(Material.AIR); }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Item addEnchant(Enchantment enchant, int value) {

        if (value > 0) {
            itemMeta.addEnchant(enchant, value, true);
        } else {
            itemMeta.removeEnchant(enchant);
        }

        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public Item Enchant(Item item, String enchant, int nivel, Player player) {

        if (enchant.equalsIgnoreCase("all")) {
            item.enchantAll(item, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("afi") || enchant.equalsIgnoreCase("sharp")) {
            item.addEnchant(Enchantment.DAMAGE_ALL, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("power") || enchant.equalsIgnoreCase("força")) {
            item.addEnchant(Enchantment.ARROW_DAMAGE, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("flame") || enchant.equalsIgnoreCase("chama")) {
            item.addEnchant(Enchantment.ARROW_FIRE, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("infinity") || enchant.equalsIgnoreCase("infinidade")) {
            item.addEnchant(Enchantment.ARROW_INFINITE, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("punch") || enchant.equalsIgnoreCase("impacto")) {
            item.addEnchant(Enchantment.ARROW_KNOCKBACK, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("bane_of_arthropods") || enchant.equalsIgnoreCase("ruina_dos_artropodes")) {
            item.addEnchant(Enchantment.DAMAGE_ARTHROPODS, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("smite") || enchant.equalsIgnoreCase("julgamento")) {
            item.addEnchant(Enchantment.DAMAGE_UNDEAD, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("depth_strider") || enchant.equalsIgnoreCase("passos_profundos")) {
            item.addEnchant(Enchantment.DEPTH_STRIDER, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("efi") || enchant.equalsIgnoreCase("eficiencia")) {
            item.addEnchant(Enchantment.DIG_SPEED, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("inq") || enchant.equalsIgnoreCase("umb")) {
            item.addEnchant(Enchantment.DURABILITY, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("fire_aspect") || enchant.equalsIgnoreCase("aspecto_flamejante")) {
            item.addEnchant(Enchantment.FIRE_ASPECT, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("repulsão") || enchant.equalsIgnoreCase("knockback")) {
            item.addEnchant(Enchantment.KNOCKBACK, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("fortune") || enchant.equalsIgnoreCase("fortuna")) {
            item.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("looting") || enchant.equalsIgnoreCase("pilhagem")) {
            item.addEnchant(Enchantment.LOOT_BONUS_MOBS, nivel);
            return this;
        }


        if (enchant.equalsIgnoreCase("respiração") || enchant.equalsIgnoreCase("respiration") || enchant.equalsIgnoreCase("resp")) {
            item.addEnchant(Enchantment.OXYGEN, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("p") || enchant.equalsIgnoreCase("protection") || enchant.equalsIgnoreCase("proteção")) {
            item.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("blast_protec") || enchant.equalsIgnoreCase("proteção_contra_explosões")) {
            item.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("feather_falling") || enchant.equalsIgnoreCase("peso_pena")) {
            item.addEnchant(Enchantment.PROTECTION_FALL, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("fire_protec") || enchant.equalsIgnoreCase("proteção_contra_fogo")) {
            item.addEnchant(Enchantment.PROTECTION_FIRE, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("projectile_protec") || enchant.equalsIgnoreCase("proteção_contra_projéteis")) {
            item.addEnchant(Enchantment.PROTECTION_PROJECTILE, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("silk") || enchant.equalsIgnoreCase("toque_suave")) {
            item.addEnchant(Enchantment.SILK_TOUCH, nivel);
            return this;
        }

        if (enchant.equalsIgnoreCase("thorns") || enchant.equalsIgnoreCase("espinhos")) {
            item.addEnchant(Enchantment.THORNS, nivel);
            return this;
        }
        if (enchant.equalsIgnoreCase("aqua_affin") || enchant.equalsIgnoreCase("afinidade_aquática")) {
            item.addEnchant(Enchantment.WATER_WORKER, nivel);
            return this;
        }
        player.sendMessage("§cOpção inválida, para ver as opções digite: §9/item ajuda enchant§c.");
        return this;
    }

    public Item enchantAll(Item item, int nivel) {

        item.addEnchant(Enchantment.DAMAGE_ALL, nivel);

        item.addEnchant(Enchantment.ARROW_DAMAGE, nivel);

        item.addEnchant(Enchantment.ARROW_FIRE, nivel);

        item.addEnchant(Enchantment.ARROW_INFINITE, nivel);

        item.addEnchant(Enchantment.ARROW_KNOCKBACK, nivel);

        item.addEnchant(Enchantment.DAMAGE_ARTHROPODS, nivel);

        item.addEnchant(Enchantment.DAMAGE_UNDEAD, nivel);

        item.addEnchant(Enchantment.DEPTH_STRIDER, nivel);

        item.addEnchant(Enchantment.DIG_SPEED, nivel);

        item.addEnchant(Enchantment.DURABILITY, nivel);

        item.addEnchant(Enchantment.FIRE_ASPECT, nivel);

        item.addEnchant(Enchantment.KNOCKBACK, nivel);

        item.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, nivel);

        item.addEnchant(Enchantment.LOOT_BONUS_MOBS, nivel);

        item.addEnchant(Enchantment.OXYGEN, nivel);

        item.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, nivel);

        item.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, nivel);

        item.addEnchant(Enchantment.PROTECTION_FALL, nivel);

        item.addEnchant(Enchantment.PROTECTION_FIRE, nivel);

        item.addEnchant(Enchantment.PROTECTION_PROJECTILE, nivel);

        item.addEnchant(Enchantment.SILK_TOUCH, nivel);

        item.addEnchant(Enchantment.THORNS, nivel);

        item.addEnchant(Enchantment.WATER_WORKER, nivel);

        return this;
    }

    public Item clearLore() {
        itemMeta.setLore(Arrays.asList(""));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public Item clearName() {
        itemMeta.setDisplayName("");
        itemStack.setItemMeta(itemMeta);
        return this;
    }

}
