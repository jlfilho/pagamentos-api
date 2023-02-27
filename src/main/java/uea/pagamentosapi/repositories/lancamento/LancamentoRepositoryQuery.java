package uea.pagamentosapi.repositories.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uea.pagamentosapi.models.Lancamento;
import uea.pagamentosapi.repositories.filter.LancamentoFilter;
import uea.pagamentosapi.repositories.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {
	
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);

}
