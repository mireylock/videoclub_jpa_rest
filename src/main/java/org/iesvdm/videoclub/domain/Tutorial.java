package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id_tutorial;
    @Column(name="titulo", length = 50)
    private String titulo;
    @Column(name="descripcion", length = 150)
    private String descripcion;
    @Column(name="publi") //si vas cambiando el nombre, como el aplication.properties tenemos spring.jpa.hibernate.ddl-auto=update, se crear un campo nuevo
    private boolean publicado;
    @Column(nullable = false) //si lo cambias, se queda null
    private Date fechaPublicacion;

    //Categoria categoria = Categoria.builder().id(100).nombre("NombreCate").build();
    //hay que ponerlo porque si no lo pones hace de muchos a muchos
    @OneToMany(mappedBy = "tutorial", fetch = FetchType.LAZY) //tutorial tiene que llamarse igual que en la clase Comentario donde está el manuToOne
    //hay dos tipos de fetch: el lazy y el eager. El lazy postpone. Por defecto si no se pone es Lazy
    //el tutorial se carga de bbdd pero los comentarios están como objetos proxy: saben como cargar, pero no se han cargado. Sólo van cuando utilizando tutorial se itera la lista de comentarios para acdeder a la info, de manera que el proxy se activa y va a la bbdd para traer la info
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comentario> comentarios;


}
