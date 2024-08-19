package com.eletronicos.jfctecnologia.eletronico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarEletronico(
		@NotNull
		Long id,
		String nome, 
		Tipo tipo, 
		int quantidade, 
		Marca marca) {

}
