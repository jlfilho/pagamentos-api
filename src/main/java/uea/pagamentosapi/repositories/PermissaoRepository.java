package uea.pagamentosapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uea.pagamentosapi.models.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}