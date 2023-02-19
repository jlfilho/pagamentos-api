package uea.pagamentosapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uea.pagamentosapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
