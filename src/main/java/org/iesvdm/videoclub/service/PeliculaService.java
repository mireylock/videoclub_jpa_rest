package org.iesvdm.videoclub.service;

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
        String buscar = buscarOrder[0].length() > 0 ? buscarOrder[0]:"";
        String order = buscarOrder[1].length() > 1 ? buscarOrder[1]:"";

        if (buscar.isEmpty()) {
            if (order.equals("asc")) {
                return this.peliculaRepository.findAllByOrderByTituloAsc();
            } else if (order.equals("desc")) {
                return this.peliculaRepository.findAllByOrderByTituloDesc();
            }
        } else if (!buscar.isEmpty()) {
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

    public List<Pelicula> allBuscaDosCampos (String[] buscarOrdenDosCampos) {
        //["campo1,sentido1", "campo2,sentido2"]
        String buscar1 = buscarOrdenDosCampos.length > 0 ? buscarOrdenDosCampos[0] : "";
        String buscar2 = buscarOrdenDosCampos.length > 1 ? buscarOrdenDosCampos[1] : "";

        String[] camposYSentidos1 = buscar1.split(",");
        String campo1 = camposYSentidos1.length > 0 ? camposYSentidos1[0] : "";
        String sentido1 = camposYSentidos1.length > 1 ? camposYSentidos1[1].trim() : "";

        String[] camposYSentidos2 = buscar2.split(",");
        String campo2 = camposYSentidos2.length > 0 ? camposYSentidos2[0] : "";
        String sentido2 = camposYSentidos2.length > 1 ? camposYSentidos2[1].trim() : "";

        if (!campo1.isEmpty() && !campo2.isEmpty()) {
            if (sentido1.equals("asc") && sentido2.equals("asc")) {
                return this.peliculaRepository.findPeliculaByTituloAndDescripcionOrderByTituloAscAndDescripcionAsc(campo1, campo2);
            } else if (sentido1.equals("asc") && sentido2.equals("desc")) {
                return this.peliculaRepository.findPeliculaByTituloAndDescripcionOrderByTituloAscAndDescripcionDesc(campo1, campo2);
            } else if (sentido1.equals("desc") && sentido2.equals("asc")) {
                return this.peliculaRepository.findPeliculaByTituloAndDescripcionOrderByTituloDescAndDescripcionAsc(campo1, campo2);
            } else if (sentido1.equals("desc") && sentido2.equals("desc")) {
                return this.peliculaRepository.findPeliculaByTituloAndDescripcionOrderByTituloDescAndDescripcionDesc(campo1, campo2);
            } else if (!sentido1.isEmpty() && sentido2.isEmpty()) {
                if (sentido1.equals("asc")) {
                    return this.peliculaRepository.findPeliculaByTituloAndDescripcionOrderByTituloAsc(campo1, campo2);
                } else if (sentido1.equals("desc")) {
                    return this.peliculaRepository.findPeliculaByTituloAndDescripcionByTituloDesc(campo1, campo2);
                }
            } else if (sentido1.isEmpty() && !sentido2.isEmpty()) {
                if (sentido2.equals("asc")) {
                    return this.peliculaRepository.findPeliculaByTituloAndDescripcionOrderByDescripcionAsc(campo1, campo2);
                } else if (sentido2.equals("desc")) {
                    return this.peliculaRepository.findPeliculaByTituloAndDescripcionOrderByDescripcionDesc(campo1, campo2);
                }
            } else {
                return this.peliculaRepository.findPeliculaByTituloAndDescripcion(campo1, campo2);
            }

        } else if (!campo1.isEmpty() && campo2.isEmpty()) {
            if (sentido1.equals("asc")) {
                return this.peliculaRepository.findPeliculaByTituloContainingIgnoreCaseOrderByTituloAsc(campo1);
            } else if (sentido1.equals("desc")) {
                return this.peliculaRepository.findPeliculaByTituloContainingIgnoreCaseOrderByTituloDesc(campo1);
            } else {
                return this.peliculaRepository.findPeliculaByTituloContainingIgnoreCase(campo1);
            }
        } else if (campo1.isEmpty() && !campo2.isEmpty()) {
            if (sentido2.equals("asc")) {
                return this.peliculaRepository.findPeliculaByDescripcionContainingIgnoreCaseOrderByDescripcionAsc(campo2);
            } else if (sentido2.equals("desc")) {
                return this.peliculaRepository.findPeliculaByDescripcionContainingIgnoreCaseOrderByDescripcionDesc(campo2);
            } else {
                return this.peliculaRepository.findPeliculaByDescripcionContainingIgnoreCase(campo2);
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
