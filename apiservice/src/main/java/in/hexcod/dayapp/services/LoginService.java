package in.hexcod.dayapp.services;

import in.hexcod.dayapp.data.UserRepository;
import in.hexcod.dayapp.models.LoginRequest;
import in.hexcod.dayapp.models.LoginResponse;
import in.hexcod.dayapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * Created by sidhant on 17-05-2016.
 */
@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        String passwordHash = DigestUtils.md5DigestAsHex(loginRequest.getPassword().getBytes());
        if(!passwordHash.equals(user.getPasswordHash())) {
            return null;
        }
        String secretStr = UUID.randomUUID().toString();
        user.setLoginSecret(secretStr);
        userRepository.save(user);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(user.getId());
        loginResponse.setSecret(secretStr);
        return loginResponse;
    }

    public boolean verifySecret(String userId, String secretStr) {
        User user = userRepository.findOne(userId);
        if(user == null)
            return false;
        return user.getLoginSecret().equals(secretStr);
    }

    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(DigestUtils.md5DigestAsHex(password.getBytes()));
        return userRepository.insert(user);
    }
}
