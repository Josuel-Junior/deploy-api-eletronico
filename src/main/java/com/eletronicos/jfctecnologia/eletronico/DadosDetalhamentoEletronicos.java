package com.eletronicos.jfctecnologia.eletronico;

public record DadosDetalhamentoEletronicos(
		long id, 
		String nome, 
		Tipo tipo,
		int qauntidade,
		Marca marca, 
		Boolean ativo) {
	
	public DadosDetalhamentoEletronicos(Eletronico eletronico) {
		this(eletronico.getId(), 
			 eletronico.getNome(),
			 eletronico.getTipo(),
			 eletronico.getQuantidade(),
			 eletronico.getMarca(),eletronico.getAtivo());
	}

}
