package uea.pagamentosapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uea.pagamentosapi.models.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
