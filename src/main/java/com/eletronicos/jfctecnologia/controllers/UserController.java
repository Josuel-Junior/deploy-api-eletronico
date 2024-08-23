package com.eletronicos.jfctecnologia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eletronicos.jfctecnologia.eletronico.DadosCadastroUser;
import com.eletronicos.jfctecnologia.eletronico.UserService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Operation(summary = "Cadastrar usuário", tags = "User")
    public ResponseEntity<?> register(@RequestBody @Valid DadosCadastroUser dadosCadastroUser) {
        Boolean savedUser = userService.saveUser(dadosCadastroUser);
        if (savedUser) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrato com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário ja cadastrato");
        }
    }

}
