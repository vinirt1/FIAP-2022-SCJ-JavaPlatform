package telegram_bot;

public class BotUtil {
    public static boolean isCommandMessage(String userMessage) {
        return userMessage.startsWith("/");
    }
}
