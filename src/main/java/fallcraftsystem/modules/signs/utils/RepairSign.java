package fallcraftsystem.modules.signs.utils;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class RepairSign {

    String[] lines;
    Player player;
    float price;

    Sign sign;
    public RepairSign(String[] lines, Player player) {
        this.player = player;
        this.lines = lines;
    }

    public RepairSign(Sign sign) {
        this.sign = sign;
        this.lines = sign.getLines();
    }

    public void noPerm(){
        player.sendMessage("§cSem Permissão!");
        lines[0] = "§4§l---------------";
        lines[1] = "§4§lSem";
        lines[2] = "§4§lPermissão";
        lines[3] = "§4§l---------------";
    }

    public void invalidSign(){
        player.sendMessage("§cPlaca inválida!");
        lines[0] = "§4§l---------------";
        lines[1] = "§4§lPlaca";
        lines[2] = "§4§lInválida";
        lines[3] = "§4§l---------------";

    }

    public boolean isRepair(){
        return lines[0].equalsIgnoreCase("[reparar]") || lines[0].equalsIgnoreCase("[§1reparar§0]");
    }

    public boolean playerHasPerm() {
        if (!player.hasPermission("repairsign.create")){
            noPerm();
            return false;
        }
        return true;
    }

    public boolean isValid() {

        if (!(lines[1].equalsIgnoreCase("mão") || lines[1].equalsIgnoreCase("tudo"))) return false;

        if (!isNumeric(lines[2])) return false;

        return lines[3].equalsIgnoreCase("");
    }

    public static boolean isNumeric(String line){
        char[] chars = line.toCharArray();

        for(char ch : chars){
            if (!Character.isDigit(ch)) return false;
        }

        return true;
    }

    public boolean isAllBool(){
        return lines[1].equalsIgnoreCase("Tudo");
    }

    public void signFormat(){
        lines[0] = "[§1Reparar§0]";
        lines[1] =  isAllBool() ? "Tudo" : "Mão";
        lines[2] = "§2$" + lines[2];
    }

    // Botei coisa pra caralho só por precaução e preguiça de testar um por um
    public float getPrice(){
        return Float.parseFloat(lines[2].replace("$", "0").replace("§", "0").replace("2", "0"));
    }
}