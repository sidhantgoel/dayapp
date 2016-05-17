package in.hexcod.dayapp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sidhant on 17-05-2016.
 */
@Document
public class Account {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private int give;
    private int take;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTake() {
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }

    public int getGive() {
        return give;
    }

    public void setGive(int give) {
        this.give = give;
    }
}
