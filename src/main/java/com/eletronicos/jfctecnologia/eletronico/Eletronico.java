package com.eletronicos.jfctecnologia.eletronico;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "eletronico")
@Table(name = "Eletronico")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Eletronico {

	public Eletronico(DadosCadastroEletronico dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.tipo = dados.tipo();
		this.quantidade = dados.quantidade();
		this.marca = dados.marca();
	
	}

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome; 
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	private int quantidade;
	
	@Enumerated(EnumType.STRING)
	private Marca marca;
	
	private Boolean ativo;

	public void atualizarInformações(@Valid DadosAtualizarEletronico dados) {
		
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		if(dados.tipo() != null) {
			this.tipo = dados.tipo();
		}
		if(dados.marca() != null) {
			this.marca = dados.marca();
		}
		
		
	}

	public void inativar() {
		this.ativo = false;
	}

	public void reativar() {
		this.ativo = true;
		
	}
}
