package uea.pagamentosapi.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import uea.pagamentosapi.model.Pessoa;
import uea.pagamentosapi.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> pessoas = pessoaService.listar();
		return ResponseEntity.ok().body(pessoas);
	}

	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.criar(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(pessoaSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(pessoaSalva);
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable Long codigo) {
		Pessoa pessoa = pessoaService.buscarPorCodigo(codigo);

		return pessoa != null ? ResponseEntity.ok().body(pessoa) : ResponseEntity.notFound().build();
	}

	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
		pessoaService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}

}
