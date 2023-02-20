package uea.pagamentosapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import uea.pagamentosapi.models.Lancamento;
import uea.pagamentosapi.services.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping
	public ResponseEntity<List<Lancamento>> listar() {
		List<Lancamento> lancamentos = lancamentoService.listar();
		return ResponseEntity.ok().body(lancamentos);
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento) {
		Lancamento lancamentoSalva = lancamentoService.criar(lancamento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(lancamentoSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(lancamentoSalva);
	}
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable Long codigo) {
		Lancamento lancamento = lancamentoService.buscarPorCodigo(codigo);
		
		return lancamento != null ? ResponseEntity.ok().body(lancamento) : ResponseEntity.notFound().build();
	}
	
	

}