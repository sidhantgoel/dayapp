package in.hexcod.dayapp.controllers.api;

import in.hexcod.dayapp.models.ApiResponse;
import in.hexcod.dayapp.models.LoginRequest;
import in.hexcod.dayapp.models.LoginResponse;
import in.hexcod.dayapp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sidhant on 17-05-2016.
 */
@RestController
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.login(loginRequest);
        if(loginResponse == null) {
            return ApiResponse.failure().build();
        }
        return ApiResponse.success().object(loginResponse);
    }
}
