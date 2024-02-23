package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    public List<Pelicula> findPeliculaByTituloContainingIgnoreCase(String buscar);
    public List<Pelicula> findPeliculaByTituloContainingIgnoreCaseOrderByTituloAsc(String buscar);
    public List<Pelicula> findPeliculaByTituloContainingIgnoreCaseOrderByTituloDesc(String buscar);
    List<Pelicula> findAllByOrderByTituloAsc();
    List<Pelicula> findAllByOrderByTituloDesc();

}
