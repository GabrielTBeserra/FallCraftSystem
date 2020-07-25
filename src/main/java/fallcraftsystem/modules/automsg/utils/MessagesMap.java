package fallcraftsystem.modules.automsg.utils;

import java.util.HashMap;

public class MessagesMap {

    private static HashMap<Integer, Messages> messages = new HashMap<>();

    public static HashMap <Integer, Messages> getMessages() {
        return messages;
    }

    public static void setMessages(HashMap<Integer, Messages> messages) {
        MessagesMap.messages = messages;
    }
}
