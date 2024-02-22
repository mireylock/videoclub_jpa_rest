package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    public List<Pelicula> findPeliculaByTituloContainingIgnoreCase(String titulo);
    List<Pelicula> findAllByOrderByTituloAsc();
    List<Pelicula> findAllByOrderByTituloDesc();

}
