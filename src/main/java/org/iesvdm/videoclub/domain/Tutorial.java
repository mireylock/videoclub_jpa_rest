package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name="tutorial",
        schema="videoclub_jpa",
        indexes = {@Index(name="tutorialIndex", columnList = "titulo")}
)
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="titulo", length = 50)
    private String titulo;
    @Column(name="descripcion", length = 150)
    private String descripcion;
    @Column(name="publi") //si vas cambiando el nombre, como el aplication.properties tenemos spring.jpa.hibernate.ddl-auto=update, se crear un campo nuevo
    private boolean publicado;
    @Column(nullable = false) //si lo cambias, se queda null
    private Date fechaPublicacion;

    //Categoria categoria = Categoria.builder().id(100).nombre("NombreCate").build();
    @OneToMany(mappedBy = "tutorial")
    private List<Comentario> comentarios;


}
