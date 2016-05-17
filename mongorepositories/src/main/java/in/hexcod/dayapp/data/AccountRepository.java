package in.hexcod.dayapp.data;

import in.hexcod.dayapp.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by sidhant on 17-05-2016.
 */
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByName(String name);
}
