package fallcraftsystem.modules.coinshop.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Item {
    private String name;
    private List<String> desc;
    private ItemMeta itemMeta;
    private ItemStack itemStack;
    private Material material;
    private short id;

    public Item(String name, List<String> desc, Material material, short id) {
        this.name = name;
        this.desc = desc;
        this.material = material;
        this.id = id;

        itemStack = new ItemStack(material, 1, id);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(desc);
        itemStack.setItemMeta(itemMeta);
    }

    public Item(String name, List<String> desc, ItemStack itemStack) {
        this.name = name;
        this.desc = desc;
        this.itemStack = itemStack;
        itemMeta = itemStack.getItemMeta();
    }

    public Item(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public Item(Material material, short id) {
        this.material = material;
        itemStack = new ItemStack(material, 1, id);
    }

    // ItemMeta
    public void setItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
        itemStack.setItemMeta(itemMeta);
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }


    // ItemStack
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        itemMeta = itemStack.getItemMeta();
    }

    public ItemStack getItemStack() {
        return itemStack;
    }


    // Name
    public void setName(String name) {
        this.name = name;
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
    }

    public String getName() {
        itemMeta.getDisplayName();
        return name;
    }


    // Description
    public void setDesc(List<String> desc) {
        this.desc = desc;
        itemMeta.setLore(desc);
        itemStack.setItemMeta(itemMeta);
    }

    public List<String> getDesc() {
        return desc;
    }


    // Material
    public void setMaterial(Material material) {
        this.material = material;
        itemStack.setType(material);
        itemMeta = itemStack.getItemMeta();
    }

    public Material getMaterial() {
        return material;
    }


    // Id
    public short getId() {
        return id;
    }

}
