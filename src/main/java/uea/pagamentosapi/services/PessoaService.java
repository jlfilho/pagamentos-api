package uea.pagamentosapi.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uea.pagamentosapi.models.Endereco;
import uea.pagamentosapi.models.Lancamento;
import uea.pagamentosapi.models.Pessoa;
import uea.pagamentosapi.repositories.PessoaRepository;
import uea.pagamentosapi.repositories.filter.LancamentoFilter;
import uea.pagamentosapi.repositories.filter.PessoaFilter;
import uea.pagamentosapi.services.exceptions.ObjectNotFountException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	public Page<Pessoa> pesquisar(PessoaFilter pessoaFilter, Pageable pageable) {
		return pessoaRepository.filtrar(pessoaFilter, pageable);
	}

	public Pessoa criar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public Pessoa buscarPorCodigo(Long codigo) {
		try {
			Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
			return pessoa.get();
		} catch (NoSuchElementException e) {
			throw new ObjectNotFountException(codigo);
		}
	}

	public void deletar(Long codigo) {
		pessoaRepository.deleteById(codigo);
	}

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaRepository.getReferenceById(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}
	
	public Pessoa atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = pessoaRepository.getReferenceById(codigo);
		pessoaSalva.setAtivo(ativo);
		return pessoaRepository.save(pessoaSalva);
	}
	
	public Pessoa atualizarEndereco(Long codigo, Endereco endereco) {
		Pessoa pessoaSalva = pessoaRepository.getReferenceById(codigo);
		pessoaSalva.setEndereco(endereco);
		return pessoaRepository.save(pessoaSalva);
	}

}
