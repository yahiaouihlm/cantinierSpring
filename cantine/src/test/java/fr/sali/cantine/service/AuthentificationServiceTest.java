package fr.sali.cantine.service;

import fr.sali.cantine.dto.in.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthentificationServiceTest {


    @Autowired
    private AuthentificationService service;

    @Test
    public void   AuthentificationService (){
        UserDto userdto =  new UserDto();
        userdto.setEmail("yahiaouihalim@social.aston-ecole.com");
        userdto.setPassword("test33");

         var id =  service.authentification(userdto) ;

         System.out.println(id);

    }
}