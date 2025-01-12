package com.demo.social_media_project.controller;

import com.demo.social_media_project.config.JwtProvider;
import com.demo.social_media_project.models.User;
import com.demo.social_media_project.repository.UserRepository;
import com.demo.social_media_project.request.LoginRequest;
import com.demo.social_media_project.response.AuthResponse;
import com.demo.social_media_project.service.CustomUserDetailsService;
import com.demo.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        User isExist = userRepository.findByEmail(user.getEmail());
        if(isExist != null){
            throw new Exception("Email is associated with another account");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token,"Register success");

        return res;
    }
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token,"Login success");

        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if(userDetails == null) throw new BadCredentialsException("Invalid Email");

        if(!passwordEncoder.matches(password,userDetails.getPassword())) throw new BadCredentialsException("Wrong Password");

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
