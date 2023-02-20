package uea.pagamentosapi.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.pagamentosapi.models.Categoria;
import uea.pagamentosapi.repositories.CategoriaRepository;
import uea.pagamentosapi.services.exceptions.ObjectNotFountException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	public Categoria criar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	} 
	
	public Categoria buscarPorCodigo(Long codigo) {
		try {
			Optional<Categoria> categoria = categoriaRepository.findById(codigo); 
			return categoria.get();			
		} catch (NoSuchElementException e) {
			throw new ObjectNotFountException(codigo);
		}
	}

}
