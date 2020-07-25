package fallcraftsystem.modules.automsg.utils;

import java.util.HashMap;

public class LoadMessages {
    public LoadMessages() {
        loadMessages();
    }

    public static void loadMessages() {
        int quantidade = MessagesConfig.getMessagesFile().getInt("quantidade");
        HashMap<Integer, Messages> messagesHashMap = new HashMap<>();

        for (int i = 1; i <= quantidade; i++) {

            int seconds = MessagesConfig.getMessagesFile().getInt("mensagens." + i + ".tempo");
            String message = MessagesConfig.getMessagesFile().getString("mensagens." + i + ".texto");
            int delay = MessagesConfig.getMessagesFile().getInt("mensagens." + i + ".delay");

            messagesHashMap.put(i, new Messages(seconds, message, delay));
        }
        MessagesMap.setMessages(messagesHashMap);
    }
}
