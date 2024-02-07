package org.iesvdm.videoclub;

import jakarta.transaction.Transactional;
import org.iesvdm.videoclub.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;
    
    @Test
    void contextLoads() {
    }
    
    @Test
    //hay que poner transactional para que no se cierre la sesión y acceda lazy a los datos
    @Transactional
    void pruebaOneToManyTutorial() {
        var tutorialList = tutorialRepository.findAll();

        tutorialList.forEach(System.out::println);
    }

}
