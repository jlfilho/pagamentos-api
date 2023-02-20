package uea.pagamentosapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uea.pagamentosapi.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
