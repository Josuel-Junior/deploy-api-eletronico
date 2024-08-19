package com.eletronicos.jfctecnologia.eletronico;

public record DadoListagemEletronicos(Long id, String nome, Tipo tipo, int quantidade, Marca marca) {
	

	public DadoListagemEletronicos(Eletronico eletronico) {
		this(eletronico.getId(),eletronico.getNome(), eletronico.getTipo(),eletronico.getQuantidade(),eletronico.getMarca());
	}

}
