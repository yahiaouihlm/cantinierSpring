package fr.sali.cantine.service;

import fr.sali.cantine.dto.in.UserDtoIn;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class InscriptionServiceTest {

    @Autowired
    private InscriptionService service ;

    @Test
    public   void TestInscription () throws  Exception {
        UserDtoIn userdto =  new UserDtoIn();
        userdto.setBirthday(LocalDate.of( 2000,07 , 18));
        userdto.setEmail("yahiaouihalim@social.aston-ecole.com");
        userdto.setPassword("test33");
        userdto.setUsername("halim");
        userdto.setUserfname("yahiaoui");
            service.inscription(userdto);

        }
       // UserEntity usertest = new UserEntity("halim" ,  "yahiaoui" ,  "yahiaouihalim@social.aston-ecole", LocalDate.of( 2000,07 , 18),  "test33") ;

    }

