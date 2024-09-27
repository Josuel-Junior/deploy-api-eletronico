package com.eletronicos.jfctecnologia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eletronicos.jfctecnologia.cadastroUsuario.DadosCadastroUser;
import com.eletronicos.jfctecnologia.cadastroUsuario.UserService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
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

    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
