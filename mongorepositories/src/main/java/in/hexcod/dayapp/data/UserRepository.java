package in.hexcod.dayapp.data;

import in.hexcod.dayapp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by sidhant on 17-05-2016.
 */
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
