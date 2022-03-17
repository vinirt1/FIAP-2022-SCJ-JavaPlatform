package telegram_bot.model;

public class GenderizeApiResponse {

    private String name;

    private String gender;

    private Double probability;

    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "GenderizeApiResponse [count=" + count + ", gender=" + gender + ", name=" + name + ", probability="
                + probability + "]";
    }

}
