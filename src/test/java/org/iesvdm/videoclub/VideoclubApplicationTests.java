package org.iesvdm.videoclub;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Idioma;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.IdiomaRepository;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    IdiomaRepository idiomaRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void crearIdiomaCategoriaPeli() {
        Idioma idioma1 = new Idioma();
        idioma1.setNombre("Inglés");
        idiomaRepository.save(idioma1);

        Idioma idioma2 = new Idioma();
        idioma2.setNombre("Francés");
        idiomaRepository.save(idioma2);

        Categoria cat1 = new Categoria();
        cat1.setNombre("Drama");
        categoriaRepository.save(cat1);

        Categoria cat2 = new Categoria();
        cat2.setNombre("Suspense");
        categoriaRepository.save(cat2);

        Categoria cat3 = new Categoria();
        cat2.setNombre("Crimen");
        categoriaRepository.save(cat3);

        Set<Categoria> categoriasPeli1 = new HashSet<>();
        categoriasPeli1.add(cat1);

        Set<Categoria> categoriasPeli2 = new HashSet<>();
        categoriasPeli2.add(cat2);
        categoriasPeli2.add(cat3);

        Pelicula peli1 = new Pelicula();
        peli1.setTitulo("La sociedad de la nieve");
        peli1.setIdioma(idioma1);
        peli1.setCategorias(categoriasPeli1);

        Pelicula peli2 = new Pelicula();
        peli2.setTitulo("Anatomía de una caída");
        peli2.setIdioma(idioma2);
        peli2.setCategorias(categoriasPeli2);

        peliculaRepository.save(peli1);
        peliculaRepository.save(peli2);

    }

    @Test
    void guardarCategoriaPeli() {




    }



}
