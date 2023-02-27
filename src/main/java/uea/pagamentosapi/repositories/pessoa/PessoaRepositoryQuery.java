package uea.pagamentosapi.repositories.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uea.pagamentosapi.models.Pessoa;
import uea.pagamentosapi.repositories.filter.LancamentoFilter;
import uea.pagamentosapi.repositories.filter.PessoaFilter;
import uea.pagamentosapi.repositories.projection.ResumoLancamento;

public interface PessoaRepositoryQuery {
	public Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable);

}
