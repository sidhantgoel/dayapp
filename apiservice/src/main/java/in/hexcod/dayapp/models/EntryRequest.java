package in.hexcod.dayapp.models;

/**
 * Created by sidhant on 17-05-2016.
 */
public class EntryRequest {
    private String name;
    private String date;
    private int give;
    private int take;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getGive() {
        return give;
    }

    public void setGive(int give) {
        this.give = give;
    }

    public int getTake() {
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }
}
