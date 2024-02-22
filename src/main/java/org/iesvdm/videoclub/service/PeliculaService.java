package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> all() {
        return this.peliculaRepository.findAll();
    }

    public List<Pelicula> all(Optional<String> buscar, Optional<String> ordenar) {
        if (buscar.isPresent() && ordenar.isPresent()) {
            if(ordenar.get().equals("asc")) {
                return this.peliculaRepository.findAllByOrderByTituloAsc();
            } else {
                return this.peliculaRepository.findPeliculaByTituloContainingIgnoreCase(buscar.get());
            }
        } else if (!buscar.isPresent() && ordenar.isPresent()) {
            if (ordenar.get().equals("asc")) {
                return this.peliculaRepository.findAllByOrderByTituloAsc();
            } else {
                return this.peliculaRepository.findAllByOrderByTituloDesc();
            }
        } else if (buscar.isPresent() && !ordenar.isPresent()) {
            return this.peliculaRepository.findPeliculaByTituloContainingIgnoreCase(buscar.get());
        } else {
            return this.peliculaRepository.findAll();
        }
    }


    public Pelicula save(Pelicula pelicula) {
        return this.peliculaRepository.save(pelicula);
    }

    public Pelicula one(Long id) {
        return this.peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula replace(Long id, Pelicula pelicula) {

        return this.peliculaRepository.findById(id).map( p -> (id.equals(pelicula.getId())  ?
                                                            this.peliculaRepository.save(pelicula) : null))
                .orElseThrow(() -> new PeliculaNotFoundException(id));

    }

    public void delete(Long id) {
        this.peliculaRepository.findById(id).map(p -> {this.peliculaRepository.delete(p);
                                                        return p;})
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }



}
