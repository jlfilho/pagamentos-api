package uea.pagamentosapi.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.pagamentosapi.models.Lancamento;
import uea.pagamentosapi.models.Pessoa;
import uea.pagamentosapi.repositories.LancamentoRepository;
import uea.pagamentosapi.services.exceptions.ObjectNotFountException;
import uea.pagamentosapi.services.exceptions.PessoaInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	public List<Lancamento> listar() {
		return lancamentoRepository.findAll();
	}
	
	public Lancamento criar(Lancamento lancamento) {
		Pessoa pessoaSalva = pessoaService.buscarPorCodigo(lancamento.getPessoa().getCodigo()); 
		if (!pessoaSalva.isAtivo()) {
			throw new PessoaInativaException(pessoaSalva.getCodigo());
		}
		return lancamentoRepository.save(lancamento);			
	} 
	
	public Lancamento buscarPorCodigo(Long codigo) {
		try {
			Optional<Lancamento> lancamento = lancamentoRepository.findById(codigo); 
			return lancamento.get();			
		} catch (NoSuchElementException e) {
			throw new ObjectNotFountException(codigo);
		}
	}

}
