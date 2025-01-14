package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.dto.CategoriaDTO;
import org.iesvdm.videoclub.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar", "!pagina", "!tamanio"})
    public List<Categoria> all() {
        log.info("Accediendo a todas las categorias");
//        return this.categoriaService.all();

        return this.categoriaService.all().stream().map(
                categoria -> {
                    int conteo = categoria.getPeliculas().size();
                    return new CategoriaDTO(categoria, conteo);
                }
        ).collect(Collectors.toList());
    }

    //Para rutas: http://localhost:8080/categorias?buscar=campo&order=desc
    @GetMapping(value = {"", "/"}, params = {"!pagina", "!tamanio"})
    public List<Categoria> all (Optional<String> buscar, Optional<String> order) {
        log.info("Accediendo a todas las categorías con filtro buscar: %s");
        return this.categoriaService.all(buscar, order);
    }

    //Para rutas http://localhost:8080/categorias?pagina=0&tamanio=2
    @GetMapping(value = {"", "/"})
    public ResponseEntity<Map<String, Object>> all (@RequestParam(value="pagina", defaultValue = "0") int pagina,
                                                    @RequestParam(value="tamanio", defaultValue = "3") int tamanio) {
        log.info("Accediendo a categorias con paginación");
        Map<String, Object> responseAll = this.categoriaService.all(pagina, tamanio);
        return ResponseEntity.ok(responseAll);
    }


    @PostMapping({"","/"})
    public Categoria newCategoria(@RequestBody Categoria categoria) {
        return this.categoriaService.save(categoria);
    }

    @GetMapping("/{id}")
    public Categoria one(@PathVariable("id") Long id) {
        return this.categoriaService.one(id);
    }

    @PutMapping("/{id}")
    public Categoria replaceCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        return this.categoriaService.replace(id, categoria);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
    }


}
