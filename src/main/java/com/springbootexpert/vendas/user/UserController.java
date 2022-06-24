package com.springbootexpert.vendas.user;

import com.springbootexpert.vendas.exception.InvalidPasswordException;
import com.springbootexpert.vendas.security.jwt.JwtService;
import com.springbootexpert.vendas.user.dto.CredencialDTO;
import com.springbootexpert.vendas.user.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserData save(@RequestBody @Valid UserData userData){
        String encryptedPassword = passwordEncoder.encode(userData.getPassword());
        userData.setPassword(encryptedPassword);
        return userService.save(userData);
    }

    @PostMapping("/auth")
    public TokenDTO authenticate (@RequestBody CredencialDTO credencialDTO){
        try{
            var userBuild = UserData.builder()
                    .login(credencialDTO.getLogin())
                    .password(credencialDTO.getPassword())
                    .build();
            UserDetails userAuthenticated = userService.authenticate(userBuild);
            var token = jwtService.generateToken(userBuild);

            return new TokenDTO(userBuild.getLogin(), token);

        } catch (UsernameNotFoundException | InvalidPasswordException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
