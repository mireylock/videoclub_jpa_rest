package org.iesvdm.videoclub;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Idioma;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.IdiomaRepository;
import org.iesvdm.videoclub.repository.PeliculaCustomQuery;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    IdiomaRepository idiomaRepository;

    @Autowired
    PeliculaCustomQuery peliculaCustomQuery;

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

        Idioma idioma3 = new Idioma();
        idioma3.setNombre("Alemán");
        idiomaRepository.save(idioma3);

        Categoria cat1 = new Categoria();
        cat1.setNombre("Drama");
        cat1.setUltimaActualizacion(new Date(2024 - 1900, 1, 18, 21, 30, 0));

        categoriaRepository.save(cat1);

        Categoria cat2 = new Categoria();
        cat2.setNombre("Suspense");
        categoriaRepository.save(cat2);

        Categoria cat3 = new Categoria();
        cat3.setNombre("Crimen");
        categoriaRepository.save(cat3);

        Categoria cat4 = new Categoria();
        cat4.setNombre("Romance");
        categoriaRepository.save(cat4);

        Categoria cat5 = new Categoria();
        cat5.setNombre("Thriller");
        categoriaRepository.save(cat5);


        Set<Categoria> categoriasPeli1 = new HashSet<>();
        categoriasPeli1.add(cat1);
        Pelicula peli1 = new Pelicula();
        peli1.setTitulo("La sociedad de la nieve");
        peli1.setIdioma(idioma1);
        peli1.setCategorias(categoriasPeli1);
        peliculaRepository.save(peli1);


        Set<Categoria> categoriasPeli2 = new HashSet<>();
        categoriasPeli2.add(cat2);
        categoriasPeli2.add(cat3);
        Pelicula peli2 = new Pelicula();
        peli2.setTitulo("Anatomía de una caída");
        peli2.setIdioma(idioma2);
        peli2.setCategorias(categoriasPeli2);
        peliculaRepository.save(peli2);

        Set<Categoria> categoriasPeli3 = new HashSet<>();
        categoriasPeli2.add(cat3);
        categoriasPeli2.add(cat4);
        Pelicula peli3 = new Pelicula();
        peli3.setTitulo("Tenet");
        peli3.setIdioma(idioma3);
        peli3.setCategorias(categoriasPeli3);
        peliculaRepository.save(peli3);

        Set<Categoria> categoriasPeli4 = new HashSet<>();
        categoriasPeli4.add(cat1);
        categoriasPeli4.add(cat4);
        Pelicula peli4 = new Pelicula();
        peli4.setTitulo("Dune II");
        peli4.setIdioma(idioma2);
        peli4.setCategorias(categoriasPeli4);
        peliculaRepository.save(peli4);

    }

    @Test
    void crearCategoria() {
        Date fecha = new Date();

        Categoria cat1 = new Categoria(0, "Romantico", new HashSet<>(), fecha);
        categoriaRepository.save(cat1);

    }

    @Test
    void peliculaCustomQuery() {
        List<Pelicula> pelisExpected = this.peliculaCustomQuery.findPelicula(Optional.of("La"), Optional.of("asc"));

        pelisExpected.forEach(System.out::println);
    }


}
