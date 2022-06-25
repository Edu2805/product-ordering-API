package com.springbootexpert.vendas.controller;

import com.springbootexpert.vendas.exception.InvalidPasswordException;
import com.springbootexpert.vendas.security.jwt.JwtService;
import com.springbootexpert.vendas.user.UserData;
import com.springbootexpert.vendas.user.UserService;
import com.springbootexpert.vendas.user.dto.CredencialDTO;
import com.springbootexpert.vendas.user.dto.TokenDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Api("Api Cadastro de usuários e geração de tokens de acesso")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Cadastrar um usuário")
    @ApiResponses({
            @ApiResponse(code= 201, message ="Usuário cadastrado com sucesso"),
            @ApiResponse(code=400, message = "Erro de validação")
    })
    public UserData save(@RequestBody @Valid UserData userData){
        String encryptedPassword = passwordEncoder.encode(userData.getPassword());
        userData.setPassword(encryptedPassword);
        return userService.save(userData);
    }

    @PostMapping("/auth")
    @ApiOperation("Gera o token de acesso após cadastro de um usuário")
    @ApiResponses({
            @ApiResponse(code= 201, message ="Token gerado com sucesso"),
            @ApiResponse(code=400, message = "Erro de validação")
    })
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
