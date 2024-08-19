package com.eletronicos.jfctecnologia.eletronico;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEletronico(
		
		
		@NotBlank
		String nome,
		
		@Enumerated
		Tipo tipo, 
		
		int quantidade,
		
		@Enumerated
		Marca marca
		) {

}
