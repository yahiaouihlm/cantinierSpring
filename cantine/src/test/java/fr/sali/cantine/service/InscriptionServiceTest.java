package fr.sali.cantine.service;

import fr.sali.cantine.dto.in.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class InscriptionServiceTest {

    @Autowired
    private SignUpService service ;

    @Test
    public   void   TestInscription () throws  Exception {
        UserDto userdto =  new UserDto();
        userdto.setBirthday(LocalDate.of( 2000,07 , 18));
        userdto.setEmail("yahiaoui@social.aston-ecole.com");
        userdto.setPassword("test33");
        userdto.setUsername("Birus");
        userdto.setUserfname("Sama");
        userdto.setPhone("0631990180");
            service.inscription(userdto ,  "admin");

        }
       // UserEntity usertest = new UserEntity("halim" ,  "yahiaoui" ,  "yahiaouihalim@social.aston-ecole", LocalDate.of( 2000,07 , 18),  "test33") ;

    }

