package uea.pagamentosapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uea.pagamentosapi.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
