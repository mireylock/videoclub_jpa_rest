package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Pelicula;

import java.util.List;
import java.util.Optional;

public interface PeliculaCustomQuery {
    public List<Pelicula> findPelicula (Optional<String> titulo, Optional<String> orden);
}
