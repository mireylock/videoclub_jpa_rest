package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    public List<Pelicula> findPeliculaByTituloContainingIgnoreCase(String buscar);
    public List<Pelicula> findPeliculaByTituloContainingIgnoreCaseOrderByTituloAsc(String buscar);
    public List<Pelicula> findPeliculaByTituloContainingIgnoreCaseOrderByTituloDesc(String buscar);
    public List<Pelicula> findAllByOrderByTituloAsc();
    public  List<Pelicula> findAllByOrderByTituloDesc();
    public List<Pelicula> findPeliculaByDescripcionContainingIgnoreCase(String buscar);
    public List<Pelicula> findPeliculaByDescripcionContainingIgnoreCaseOrderByDescripcionAsc(String buscar);
    public List<Pelicula> findPeliculaByDescripcionContainingIgnoreCaseOrderByDescripcionDesc(String buscar);

    @Query (value = "Select P from Pelicula P where P.titulo like %:buscar1% and P.descripcion like %:buscar2% order by P.titulo asc, P.descripcion asc")
    public List<Pelicula> findPeliculaByTituloAndDescripcionOrderByTituloAscAndDescripcionAsc(@Param("buscar1") String buscar1, @Param("buscar2") String buscar2);

    @Query (value = "Select P from Pelicula P where P.titulo like %:buscar1% and P.descripcion like %:buscar2% order by P.titulo asc, P.descripcion desc")
    public List<Pelicula> findPeliculaByTituloAndDescripcionOrderByTituloAscAndDescripcionDesc(String buscar1, String buscar2);

    @Query (value = "Select P from Pelicula P where P.titulo like %:buscar1% and P.descripcion like %:buscar2% order by P.titulo desc, P.descripcion asc")
    public List<Pelicula> findPeliculaByTituloAndDescripcionOrderByTituloDescAndDescripcionAsc(String buscar1, String buscar2);

    @Query (value = "Select P from Pelicula P where P.titulo like %:buscar1% and P.descripcion like %:buscar2% order by P.titulo desc, P.descripcion desc")
    public List<Pelicula> findPeliculaByTituloAndDescripcionOrderByTituloDescAndDescripcionDesc(String buscar1, String buscar2);
    @Query (value = "Select P from Pelicula P where P.titulo like %:buscar1% and P.descripcion like %:buscar2% order by P.titulo asc")
    public List<Pelicula> findPeliculaByTituloAndDescripcionOrderByTituloAsc(String buscar1, String buscar2);

    @Query (value = "Select P from Pelicula P where P.titulo like %:buscar1% and P.descripcion like %:buscar2% order by P.titulo desc")
    public List<Pelicula> findPeliculaByTituloAndDescripcionByTituloDesc(String buscar1, String buscar2);

    @Query (value = "Select P from Pelicula P where P.titulo like %:buscar1% and P.descripcion like %:buscar2% order by P.descripcion asc")
    public List<Pelicula> findPeliculaByTituloAndDescripcionOrderByDescripcionAsc(String buscar1, String buscar2);

    @Query (value = "Select P from Pelicula P where P.titulo like %:buscar1% and P.descripcion like %:buscar2% order by P.descripcion desc")
    public List<Pelicula> findPeliculaByTituloAndDescripcionOrderByDescripcionDesc(String buscar1, String buscar2);

    @Query (value = "Select P from Pelicula P where P.titulo like %:buscar1% and P.descripcion like %:buscar2%")
    public List<Pelicula> findPeliculaByTituloAndDescripcion(String buscar1, String buscar2);
}
