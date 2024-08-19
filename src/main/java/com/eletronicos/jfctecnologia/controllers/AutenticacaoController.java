package com.eletronicos.jfctecnologia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eletronicos.jfctecnologia.eletronico.infra.TokenService;
import com.eletronicos.jfctecnologia.eletronico.infra.DadostokenJWT;
import com.eletronicos.jfctecnologia.usuarios.DadosAutenticacao;
import com.eletronicos.jfctecnologia.usuarios.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@Operation(summary = "Efetuar login", description = "Metodo que faz login e gera token", tags = "Login")
	@PostMapping
	public ResponseEntity<?> efetuarLogin (@RequestBody @Valid DadosAutenticacao dados){
		
		
		var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		
		
	    var autenticacao = manager.authenticate(token);
	
		var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());
	    
		return ResponseEntity.ok(new DadostokenJWT(tokenJWT));
		
	}
	
}
