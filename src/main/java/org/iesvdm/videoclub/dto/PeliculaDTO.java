package org.iesvdm.videoclub.dto;

import lombok.Data;
import org.iesvdm.videoclub.domain.Pelicula;

@Data
public class PeliculaDTO extends Pelicula {
    private int conteo;

    public PeliculaDTO (Pelicula pelicula, int conteo) {
        super(pelicula.getId(), pelicula.getTitulo(), pelicula.getDescripcion(), pelicula.getAnyoLanzamiento(), pelicula.getIdioma(), pelicula.getIdiomaOriginal(), pelicula.getDuracionAlquiler(), pelicula.getRentalRate(), pelicula.getDuracion(), pelicula.getReplacementCost(), pelicula.getClasificacion(), pelicula.getCaracteristicasEspeciales(), pelicula.getCategorias(), pelicula.getUltimaActualizacion());
        this.conteo = conteo;
    }

}