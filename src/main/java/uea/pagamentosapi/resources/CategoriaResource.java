package uea.pagamentosapi.resources;

import java.net.URI;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import uea.pagamentosapi.model.Categoria;
import uea.pagamentosapi.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		List<Categoria> categorias = categoriaService.listar();
		return ResponseEntity.ok().body(categorias);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria) {
		Categoria categoriaSalva = categoriaService.criar(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(categoriaSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	

}
