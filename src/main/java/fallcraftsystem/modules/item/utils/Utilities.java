package fallcraftsystem.modules.item.utils;

import fallcraftsystem.utils.Ultilities;

import java.util.List;

public class Utilities {
    public static boolean isNumeric(char[] charArray) {
        return Character.isDigit(charArray[0]);
    }

    public static List<String> getCurrentDesc(Item item) {
        return item.getItemMeta().getLore();
    }

    public static String setDescLine(String[] args, int num) {
        String desc = "";
        for (int i = num; i < args.length; i++) {
            desc += args[i] + " ";
        }
        return Ultilities.formater(desc);
    }
}
