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

	@Operation(tags = "Salvar produto")
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoEletronicos> cadastrar(@RequestBody @Valid DadosCadastroEletronico dados,
			UriComponentsBuilder uriBulder) {

		var eletronico = new Eletronico(dados);

		repository.save(eletronico);

		var uri = uriBulder.path("/eletronico/{id}").buildAndExpand(eletronico.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoEletronicos(eletronico));
	}

	@Operation(tags = "Listar todos os produtos")
	@GetMapping
	public ResponseEntity<List<DadoListagemEletronicos>> listar() {

		var lista = repository.findAllByAtivoTrue().stream().map(DadoListagemEletronicos::new).toList();

		return ResponseEntity.ok(lista);

	}

	@Operation(tags = "Atualizar produto")
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoEletronicos> atualizar(@RequestBody @Valid DadosAtualizarEletronico dados) {
		var eletronico = repository.getReferenceById(dados.id());

		eletronico.atualizarInformações(dados);

		return ResponseEntity.ok(new DadosDetalhamentoEletronicos(eletronico));

	}

	@Operation(tags = "Deletar produto")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@Operation(tags = "Inativar produto")
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var eletronico = repository.getReferenceById(id);

		eletronico.inativar();

		return ResponseEntity.noContent().build();
	}

	@Operation(tags = "Buscar produto pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoEletronicos> detalhar(@PathVariable Long id) {

		var eletronico = repository.getReferenceById(id);

		return ResponseEntity.ok(new DadosDetalhamentoEletronicos(eletronico));

	}

	@Operation(tags = "Reativar produto pelo ID")
	@PutMapping("reativar/{id}")
	@Transactional
	public ResponseEntity<Void> reativar(@PathVariable Long id) {
		var eletronico = repository.getReferenceById(id);

		eletronico.reativar();

		return ResponseEntity.noContent().build();
	}

}
