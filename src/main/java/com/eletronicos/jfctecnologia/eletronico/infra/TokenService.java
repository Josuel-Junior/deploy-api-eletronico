package com.eletronicos.jfctecnologia.eletronico.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.eletronicos.jfctecnologia.usuarios.Usuario;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("Eletronico_api").withSubject(usuario.getLogin())
					.withExpiresAt(dataExperiração()).sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar token");
		}
	}

	public String getSubject(String tokenJWT) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm).withIssuer("Eletronico_api").build().verify(tokenJWT).getSubject();

		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Token inválido ou expirado");
		}
	}

	private Instant dataExperiração() {

		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
