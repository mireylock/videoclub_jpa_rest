package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public List<Categoria> findCategoriaByNombreContainingIgnoreCase(String buscar);
    public List<Categoria> findCategoriaByNombreContainingIgnoreCaseOrderByNombreAsc(String buscar);
    public List<Categoria> findCategoriaByNombreContainingIgnoreCaseOrderByNombreDesc(String buscar);
    List<Categoria> findAllByOrderByNombreAsc();
    List<Categoria> findAllByOrderByNombreDesc();
}
