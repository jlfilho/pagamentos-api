package uea.pagamentosapi.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uea.pagamentosapi.models.Lancamento;
import uea.pagamentosapi.models.Pessoa;
import uea.pagamentosapi.repositories.LancamentoRepository;
import uea.pagamentosapi.repositories.PessoaRepository;
import uea.pagamentosapi.repositories.filter.LancamentoFilter;
import uea.pagamentosapi.repositories.projection.ResumoLancamento;
import uea.pagamentosapi.services.exceptions.ObjectNotFountException;
import uea.pagamentosapi.services.exceptions.PessoaInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaService pessoaService;

	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}

	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.resumir(lancamentoFilter, pageable);
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

	public void deletar(Long codigo) {
		lancamentoRepository.deleteById(codigo);
	}

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = lancamentoRepository.getReferenceById(codigo);
		if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
			validarPessoa(lancamento);
		}
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
		return lancamentoRepository.save(lancamentoSalvo);
	}

	private void validarPessoa(Lancamento lancamento) {
		Optional<Pessoa> pessoa = null;
		if (lancamento.getPessoa().getCodigo() != null) {
			pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		}

		if (pessoa.get() == null || !pessoa.get().isAtivo()) {
			throw new PessoaInativaException(lancamento.getPessoa().getCodigo());
		}
	}

}
