package com.eletronicos.jfctecnologia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eletronicos.jfctecnologia.eletronico.DadosCadastroUser;
import com.eletronicos.jfctecnologia.eletronico.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public ResponseEntity<DadosCadastroUser> register(@RequestBody DadosCadastroUser dadosCadastroUser) {
        DadosCadastroUser savedUser = userService.saveUser(dadosCadastroUser);

        return ResponseEntity.ok(savedUser);
    }

}
