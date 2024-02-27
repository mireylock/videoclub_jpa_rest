package org.iesvdm.videoclub.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //puedo generar builders de categoría
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    @EqualsAndHashCode.Include
    private long id;
    private String nombre;

    @ManyToMany(mappedBy = "categorias", fetch = FetchType.EAGER,  cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JsonIgnore
    private Set<Pelicula> peliculas = new HashSet<>();

    @Column(name = "ultima_actualizacion")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  shape = JsonFormat.Shape.STRING)
    private Date ultimaActualizacion;
}