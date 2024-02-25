package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.dto.PeliculaDTO;
import org.iesvdm.videoclub.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @GetMapping(value = {"","/"}, params = {"!orden", "!paginado"})
    public List<Pelicula> all() {
        log.info("Accediendo a todas las películas");
        return this.peliculaService.all();
//        return this.peliculaService.all().stream().map(
//                pelicula -> {
//                    int conteo = pelicula.getCategorias().size();
//                    return new PeliculaDTO(pelicula, conteo);
//                }
//        ).collect(Collectors.toList());
    }

    //Para rutas http://localhost:8080/peliculas?orden=campo1,sentido1
    @GetMapping(value = {"", "/"}, params = {"!paginado"})
    public  List<Pelicula> all (String[] orden) {
        log.info("Accediendo a todas las películas con filtro buscarOrder");
        return this.peliculaService.all(orden);
    }

    //Para rutas: http://localhost:8080/peliculas?orden=campo1,sentido1&orden=campo2,sentido2
//    @GetMapping(value = {"", "/"}, params = {"!paginado", "!orden"})
//    public  List<Pelicula> allOrden (String[] orden1) {
//        log.info("Accediendo a todas las películas con filtro buscarOrder");
//        return this.peliculaService.allOrden(orden1);
//    }

    //Para rutas http://localhost:8080/peliculas?paginado=0,1
    @GetMapping(value = {"", "/"})
    public ResponseEntity<Map<String, Object>> allPag (@RequestParam(value="paginado", defaultValue = "0") String[] paginado) {
        log.info("Accediendo a películas con paginación");
        Map<String, Object> responseAll = this.peliculaService.allPag(paginado);
        return ResponseEntity.ok(responseAll);
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
