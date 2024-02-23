package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar"})
    public List<Pelicula> all() {
        log.info("Accediendo a todas las películas");
        return this.peliculaService.all();

        //Para el conteo, crear un DTO de película con publicDTO (Pelicula peli) y super(peli.getId()....) y el int conteo
//        return this.peliculaService.all().stream().map(p-> {
//            peliculaDTO peliDTO = new PeliculaDTO(p);
//            peliDTO.setConteo((int)Math.random()*100); --> aquí lanzar una query que va sacando el conteo
//            return peliDTO;
//        }).collect(Collectors.toList());

        //o con el constructor con dos campos solo hay que devolver un new PeliculaDTO


    }

    @GetMapping(value = {"", "/"})
    public List<Pelicula> all (Optional<String> buscar, Optional<String> order) {
        log.info("Accediendo a todas las películas con filtro buscar: %s");
        buscar.orElse("VOID");
        order.orElse("VOID");
        
        return this.peliculaService.all(buscar, order);
    }


    @PostMapping({"","/"})
    public Pelicula newPelicula(@RequestBody Pelicula pelicula) {
        return this.peliculaService.save(pelicula);
    }

    @GetMapping("/{id}")
    public Pelicula one(@PathVariable("id") Long id) {
        return this.peliculaService.one(id);
    }

    @PutMapping("/{id}")
    public Pelicula replacePelicula(@PathVariable("id") Long id, @RequestBody Pelicula pelicula) {
        return this.peliculaService.replace(id, pelicula);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable("id") Long id) {
        this.peliculaService.delete(id);
    }


}
