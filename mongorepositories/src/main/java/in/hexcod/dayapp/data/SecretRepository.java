package in.hexcod.dayapp.data;

import in.hexcod.dayapp.models.Secret;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by sidhant on 17-05-2016.
 */
public interface SecretRepository extends MongoRepository<Secret, String> {
}
