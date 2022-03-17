package telegram_bot.model;

public class GenderizeApiResponse {

    private String activity;

    private String type;

    private int participants;

    private Double price;

    private String link;

    private int key;

    private Double accessibility;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Double getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Double accessibility) {
        this.accessibility = accessibility;
    }

    @Override
    public String toString() {
        return "GenderizeApiResponse [accessibility=" + accessibility + ", activity=" + activity + ", key=" + key
                + ", link=" + link + ", participants=" + participants + ", price=" + price + ", type=" + type + "]";
    }

}
