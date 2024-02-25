package org.iesvdm.videoclub.dto;

import lombok.Data;
import org.iesvdm.videoclub.domain.Categoria;

@Data
public class CategoriaDTO extends Categoria {

    private int conteo;

    public CategoriaDTO (Categoria categoria, int conteo) {
        super(categoria.getId(), categoria.getNombre(), categoria.getPeliculas(), categoria.getUltimaActualizacion());
        this.conteo = conteo;
    }
    
}
