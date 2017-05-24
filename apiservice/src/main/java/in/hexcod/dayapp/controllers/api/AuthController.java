package in.hexcod.dayapp.controllers.api;

import in.hexcod.dayapp.data.UserRepository;
import in.hexcod.dayapp.models.ApiResponse;
import in.hexcod.dayapp.models.User;
import in.hexcod.dayapp.security.JwtAuthenticationRequest;
import in.hexcod.dayapp.security.JwtAuthenticationResponse;
import in.hexcod.dayapp.security.JwtTokenUtil;
import in.hexcod.dayapp.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sidhant on 17-05-2016.
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    ApiResponse<User> create() {
        User user = new User();
        user.setUsername("sidhant");
        user.setPassword(new BCryptPasswordEncoder().encode("samsung"));
        user = userRepository.save(user);
        return ApiResponse.success().object(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse<JwtAuthenticationResponse> login(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        // Return the token
        return ApiResponse.success().object(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public ApiResponse<JwtAuthenticationResponse> refresh(HttpServletRequest request) {
        String token = request.getHeader("authorization").substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ApiResponse.success().object(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ApiResponse.failure().build();
        }
    }
}
