package fallcraftsystem.modules.npc.utils;

import org.bukkit.entity.Player;

public class Falas {
    public static String fala1 = "Olá %player%! Seja bem-vindo ao GuildHall. Aqui nós iremos lhe auxiliar com a criação de sua facção entre outras coisas.";


    public static String getFala(Player player , int fala){
        switch (fala){
            case 1: return fala1.replace("%player%" , player.getName());
        }

        return "Nao definido";
    }
}
