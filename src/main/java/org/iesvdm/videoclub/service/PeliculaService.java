package org.iesvdm.videoclub.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<Pelicula> all(String[] buscarOrder) {
        String buscar = buscarOrder[0];
        String order = buscarOrder[1];

        if (buscar.equals("")) {
            if (order.equals("asc")) {
                return this.peliculaRepository.findAllByOrderByTituloAsc();
            } else if (order.equals("desc")) {
                return this.peliculaRepository.findAllByOrderByTituloDesc();
            }
        } else if (!buscar.equals("")) {
            if (order.equals("asc")) {
                return this.peliculaRepository.findPeliculaByTituloContainingIgnoreCaseOrderByTituloAsc(buscar);
            } else if (order.equals("desc")) {
                return this.peliculaRepository.findPeliculaByTituloContainingIgnoreCaseOrderByTituloDesc(buscar);

            } else {
                return this.peliculaRepository.findPeliculaByTituloContainingIgnoreCase(buscar);
            }
        }

        return  this.peliculaRepository.findAll();

    }

    public List<Pelicula> allOrden (String[] buscarOrder) {
        //["campo1,sentido1", "campo2,sentido2"]
        String buscar1 = buscarOrder[0];
        String campo1 = buscar1.split(",")[0];
        String sentido1 = buscar1.split(",")[1].trim();

        String buscar2 = buscarOrder[1];
        String campo2 = buscar2.split(",")[0];
        String sentido2 = buscar2.split(",")[1].trim();

        if (!buscar1.equals("")) {
            if (sentido1.equals("asc")) {
                return this.peliculaRepository.findPeliculaByTituloContainingIgnoreCase(buscar1);
            } else if (sentido1.equals("desc")) {
            }
        }

        return this.peliculaRepository.findAll();

    }

    public Map<String, Object> allPag (String[] paginado) {
        int pagina = Integer.parseInt(paginado[0]);
        int tamanio =  Integer.parseInt(paginado[1]);

        Pageable paginacion = PageRequest.of(pagina, tamanio, Sort.by("id").ascending());
        Page<Pelicula> pageAll = this.peliculaRepository.findAll(paginacion);

        Map<String, Object> response = new HashMap<>();

        response.put("peliculas", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
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
