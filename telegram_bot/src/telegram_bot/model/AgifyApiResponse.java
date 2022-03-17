package telegram_bot.model;

public class AgifyApiResponse {

    private String name;

    private int age;

    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AgifyApiResponse [age=" + age + ", count=" + count + ", name=" + name + "]";
    }

}
