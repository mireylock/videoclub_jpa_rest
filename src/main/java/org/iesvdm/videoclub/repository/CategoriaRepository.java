package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

//    public List<Categoria> findCategoriaByNombreContainingOrderByNombreAsc(Optional<String> nombre);
//    public List<Categoria> findCategoriaByNombreContainingOrderByNombreDesc(Optional<String> nombre);

    public List<Categoria> findCategoriaByNombreContainingIgnoreCase(String buscar);

    List<Categoria> findAllByOrderByNombreAsc();
    List<Categoria> findAllByOrderByNombreDesc();



}
