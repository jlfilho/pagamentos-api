package uea.pagamentosapi.repositories.lancamento;

import java.util.List;

import uea.pagamentosapi.models.Lancamento;
import uea.pagamentosapi.repositories.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}
