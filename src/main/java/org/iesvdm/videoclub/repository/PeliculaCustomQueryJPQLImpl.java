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


}
