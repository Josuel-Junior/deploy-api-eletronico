package com.eletronicos.jfctecnologia.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eletronicos.jfctecnologia.eletronico.DadoListagemEletronicos;
import com.eletronicos.jfctecnologia.eletronico.DadosAtualizarEletronico;
import com.eletronicos.jfctecnologia.eletronico.DadosCadastroEletronico;
import com.eletronicos.jfctecnologia.eletronico.DadosDetalhamentoEletronicos;
import com.eletronicos.jfctecnologia.eletronico.Eletronico;
import com.eletronicos.jfctecnologia.eletronico.EletronicoRepository;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/eletronicos")
public class EletronicosController {

	@Autowired
	private EletronicoRepository repository;

	@Operation(summary = "Salvar", description = "Metodo que salvar um registro", tags = "Salvar")
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoEletronicos> cadastrar(@RequestBody @Valid DadosCadastroEletronico dados,
			UriComponentsBuilder uriBulder) {

		var eletronico = new Eletronico(dados);

		repository.save(eletronico);

		var uri = uriBulder.path("/eletronico/{id}").buildAndExpand(eletronico.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoEletronicos(eletronico));
	}

	@Operation(summary = "Listar todos", description = "Metodo que lista todos os registros", tags = "Listar")
	@GetMapping
	public ResponseEntity<List<DadoListagemEletronicos>> listar() {
		
		

		var lista = repository.findAllByAtivoTrue().stream().map(DadoListagemEletronicos::new).toList();

		return ResponseEntity.ok(lista);

	}

	@Operation(summary = "Atualizar", description = "Metodo que atualiza registro", tags = "Atualizar")
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoEletronicos> atualizar(@RequestBody @Valid DadosAtualizarEletronico dados) {
		var eletronico = repository.getReferenceById(dados.id());

		eletronico.atualizarInformações(dados);

		return ResponseEntity.ok(new DadosDetalhamentoEletronicos(eletronico));

	}

	@Operation(summary = "Deletar", description = "Metodo que deleta registro", tags = "Deletar")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Inativar", description = "Metodo que inativa registro", tags = "Inativar")
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var eletronico = repository.getReferenceById(id);

		eletronico.inativar();

		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Busca por ID", description = "Metodo que retorna um registro pelo ID", tags = "Buscar por ID")
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoEletronicos> detalhar(@PathVariable Long id) {

		var eletronico = repository.getReferenceById(id);

		return ResponseEntity.ok(new DadosDetalhamentoEletronicos(eletronico));

	}

	@Operation(summary = "Reativar", description = "Metodo que ativa registro inativado", tags = "Ativar")
	@PutMapping("reativar/{id}")
	@Transactional
	public ResponseEntity<Void> reativar(@PathVariable Long id) {
		var eletronico = repository.getReferenceById(id);

		eletronico.reativar();

		return ResponseEntity.noContent().build();
	}

}
