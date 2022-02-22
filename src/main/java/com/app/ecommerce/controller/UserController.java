package com.app.ecommerce.controller;

import com.app.ecommerce.model.UserModel;
import com.app.ecommerce.security.JwtProvider;
import com.app.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    private String login(@RequestParam("email") String email, @RequestParam("password") String password) throws ResponseStatusException {
        return this.userService.login(email, password);
//        try {
//            return this.userService.login(email, password);
//        }catch (Exception e){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//        String token = jwtProvider.getJWTToken(email, jwtSecret);
//        UserRedis user = new UserRedis()
//        return user;
    }

    @PostMapping("/signup")
    private UserModel signup(@RequestParam("email")String email, @RequestParam("pass1")String pass, @RequestParam("pass2")String pass2) throws ResponseStatusException {
        try {
            return this.userService.signup(email,pass,pass2);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
