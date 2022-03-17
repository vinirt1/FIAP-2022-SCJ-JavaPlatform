package telegram_bot.model;

public class ChatFlow {
    private int interationCount;

    private String previousMessage;

    private String currentMessage;

    private int flowOption;

    public int getInterationCount() {
        return interationCount;
    }

    public void setInterationCount(int interationCount) {
        this.interationCount = interationCount;
    }

    public String getPreviousMessage() {
        return previousMessage;
    }

    public void setPreviousMessage(String previousMessage) {
        this.previousMessage = previousMessage;
    }

    public String getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
    }

    public int getFlowOption() {
        return flowOption;
    }

    public void setFlowOption(int flowOption) {
        this.flowOption = flowOption;
    }

    @Override
    public String toString() {
        return "ChatFlow [currentMessage=" + currentMessage + ", flowOption=" + flowOption + ", interationCount="
                + interationCount + ", previousMessage=" + previousMessage + "]";
    }

}
