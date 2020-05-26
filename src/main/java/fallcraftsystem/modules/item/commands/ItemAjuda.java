package fallcraftsystem.modules.item.commands;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ItemAjuda implements CommandExecutor {

    public FallCraftSystem plugin;
    CommandSender sender;

    public ItemAjuda(FallCraftSystem plugin) {
        this.plugin = plugin;

        plugin.getCommand("itemajuda").setExecutor(this);

    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        this.sender = sender;

        if (args.length == 0) {
            help();
            return true;
        }

        if (args[0].equalsIgnoreCase("enchant") || args[0].equalsIgnoreCase("enctantar")) {
            int index = args.length == 1 ? 1 : Integer.parseInt(args[1]);

            helpEnchant(index);
            return true;
        }

        sender.sendMessage("§7Digite §9/itajuda");

        return true;
    }


    private void help() {
        sender.sendMessage("§eAjuda do §9/item§e:");
        sender.sendMessage("\n§9/itemnome <nome>: §7Muda o nome do item;");
        sender.sendMessage("§9/itemdesc <descrição>: §7Muda a descrição §c(Comando apenas\npara descrições de UMA linha);");
        sender.sendMessage("§9/itemdesc <número da linha (1, 2, 3, etc)>: §7Muda a descrição adcionando uma linha específica;");
        sender.sendMessage("§9/itemapagar <nome/desc/tudo>: §7Apaga o nome/descrição/ambos do item;");
        sender.sendMessage("§9/itemenchant <id do encantamento> <nível>: §7Encanta o item com o encantamento e o nível desejados;");
        sender.sendMessage("§9/itemajuda enchant: §7Id dos encantamentos;");
    }


    private void helpEnchant(int index) {
        switch (index) {
            case 1:
                sender.sendMessage("\n§aEncantamentos Disponíveis (1/4):");
                sender.sendMessage("§9Armas: ");
                sender.sendMessage("\n§eAfiação: §7afi/sharp; §eRuína dos Artrópodes: §7bane_of_arthropods");
                sender.sendMessage("§7/ruína_dos_artrópodes; §eJulgamento: §7smite/julgamento; ");
                sender.sendMessage("§eDurabilidade: §7inq/umb; §eAspecto Flamejante: §7fire_aspect");
                sender.sendMessage("§7/aspecto_flamejante; §eRepulsão: §7knockback/repulsão; §ePilhagem: \n§7looting/pilhagem;");
                break;
            case 2:
                sender.sendMessage("\n§aEncantamentos Disponíveis (2/4):");
                sender.sendMessage("&aUse /item enchant &7<nomes em cinza>.");
                sender.sendMessage("§9Arco: ");
                sender.sendMessage("\n§eForça:§7 power/força; §eImpacto: punch/impacto§7;");
                sender.sendMessage("§eChama:§7 flame/chama; §eInfinidade: §7infinity/infinidade;");
                sender.sendMessage("§eDurabilidade: §7umb/inq;");
                break;
            case 3:
                sender.sendMessage("\n§aEncantamentos Disponíveis (3/4):");
                sender.sendMessage("§9Ferramentas:");
                sender.sendMessage("\n§eEficiência: §7efi/eficiencia; §eFortuna: §7fortune/fortuna;");
                sender.sendMessage("§eToque Suave: §7silk_touch/toque_suave; §eDurabilidade: §7umb/inq;");
                sender.sendMessage("");
                break;
            case 4:
                sender.sendMessage("\n§aEncantamentos Disponíveis (4/4):");
                sender.sendMessage("§9Armaduras");
                sender.sendMessage("\n§eProteção: §7protection/proteção/p; §eProt. Contra Expl.: ");
                sender.sendMessage("§7blast_protec/proteção_contra_explosões; §eProt. Contra Fogo: ");
                sender.sendMessage("§7fire_protec/proteção_contra_fogo; §eProt. Contra Proj: ");
                sender.sendMessage("§7projectile_protec/proteção_contra_projéteis; ");
                sender.sendMessage("§eEspinhos: §7thorns/espinhos; §eRespiração: \n§7respiration/respiração;");
                sender.sendMessage("§eAfinidade Aquática: §7aqua_affin/afinidade_aquática;");
                sender.sendMessage("§ePeso Pena: §7peso_pena/feather_falling;");
                break;
            default:
                sender.sendMessage("\n§cInválido §7digite: §9/itemajuda enchant <1-4>");
                break;
        }
    }
}