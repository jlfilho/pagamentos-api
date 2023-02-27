package uea.pagamentosapi.repositories.lancamento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uea.pagamentosapi.dto.LancamentoEstatisticaCategoria;
import uea.pagamentosapi.models.Lancamento;
import uea.pagamentosapi.repositories.filter.LancamentoFilter;
import uea.pagamentosapi.repositories.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {
	public List<LancamentoEstatisticaCategoria> porCategoria(LocalDate mesReferencia);
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);

}
