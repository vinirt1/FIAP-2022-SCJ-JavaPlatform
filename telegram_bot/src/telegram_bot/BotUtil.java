package telegram_bot;

import java.util.Objects;

public class BotUtil {
    public static boolean isCommandMessage(String userMessage) {
        return userMessage.startsWith("/");
    }

    public static boolean isNumberMessage(String userMessage) {
        if (Objects.isNull(userMessage)) {
            return false;
        }
        try {
            Integer.parseInt(userMessage);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
