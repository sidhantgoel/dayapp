package in.hexcod.dayapp.models;

/**
 * Created by sidhant on 17-05-2016.
 */
public class SummaryEntry {
    private String nameOrDate;
    private int give;
    private int take;

    public String getNameOrDate() {
        return nameOrDate;
    }

    public void setNameOrDate(String nameOrDate) {
        this.nameOrDate = nameOrDate;
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
