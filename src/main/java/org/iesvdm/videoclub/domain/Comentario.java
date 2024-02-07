package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String contenido;
    @ManyToOne
    //con las anotaciones se puede modificar bbdd. Hemos creado fKs
    @JoinColumn(name = "tutorial_id_fk", nullable = false, referencedColumnName = "id_tutorial", foreignKey = @ForeignKey(name = "FK_TUTO"))
    @ToString.Exclude //para que no se cree el toString de loombok que se crea con Data
    //sin el ToString.Exclude, el test da un error de overflow porque va de un campo al otro to do el rato
    private Tutorial tutorial;


}
