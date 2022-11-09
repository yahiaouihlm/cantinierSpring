package fr.sali.cantine.service;

import fr.sali.cantine.dto.in.UserDtoIn;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthentificationServiceTest {


    @Autowired
    private AuthentificationService service;

    @Test
    public void   AuthentificationService (){
        UserDtoIn userdto =  new UserDtoIn();
        userdto.setEmail("yahiaouihalim@social.aston-ecole.com");
        userdto.setPassword("test33");

         var id =  service.authentification(userdto) ;

         System.out.println(id);

    }
}