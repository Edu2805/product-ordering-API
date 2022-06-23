package com.springbootexpert.vendas.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserData save(@RequestBody @Valid UserData userData){
        String encryptedPassword = passwordEncoder.encode(userData.getPassword());
        userData.setPassword(encryptedPassword);
        return userService.save(userData);
    }
}
