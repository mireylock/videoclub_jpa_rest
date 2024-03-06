package org.iesvdm.videoclub.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.videoclub.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PeliculaCustomQueryJPQLImpl implements PeliculaCustomQuery {
    @Autowired
    private EntityManager em;

    @Override
    public List<Pelicula> findPelicula(Optional<String> titulo, Optional<String> orden) {
        String queryStr = "select P from Pelicula P";

        if (titulo.isPresent()) queryStr += " where P.titulo like :titulo ";

        if (orden.isPresent() && orden.get().equalsIgnoreCase("asc") || orden.get().equalsIgnoreCase("desc")) queryStr += " order by P.titulo " + orden.get();

        Query query = em.createQuery(queryStr);

        if (titulo.isPresent()) query.setParameter("titulo", "%"+titulo.get()+"%");

        return query.getResultList();
    }

    @Override
    public List<Pelicula> findPeliculaPaginacion(Optional<String> titulo, Optional<String> orden, Optional<Integer> tamanio, Optional<Integer> pagina) {
        String queryStr = "select P from Pelicula P";

        if (titulo.isPresent()) queryStr += " where P.titulo like :titulo ";

        if (orden.isPresent() && orden.get().equalsIgnoreCase("asc") || orden.get().equalsIgnoreCase("desc")) queryStr += " order by P.titulo " + orden.get();

        Query query = null;

        if (tamanio.isPresent() && pagina.isPresent()) query = em.createQuery(queryStr, Pelicula.class)
                .setMaxResults(tamanio.get())
                .setFirstResult(pagina.get() * tamanio.get());

        else query = em.createQuery(queryStr, Pelicula.class);

        if (titulo.isPresent()) query.setParameter("titulo", "%"+titulo.get());

        //total items
        int totalItems = query.getResultList().size();

        //total pages
        double totalPages = query.getResultList().size()/tamanio.get();

        return query.getResultList();
    }





}
