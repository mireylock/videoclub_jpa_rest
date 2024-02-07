package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    //con esto se crea el DAO. Hay que decirle el tipo de repositorio donde va a trabajar y el tipo del id (long)
    //es por herencia, no se hace un .class
}
