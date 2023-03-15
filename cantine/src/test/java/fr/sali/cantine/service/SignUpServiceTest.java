package fr.sali.cantine.service;

import fr.sali.cantine.dto.in.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SignUpServiceTest {

    @Autowired
    SignUpService signUpService;

    @Test
    public void  SignUpServiceTest () throws Exception {
        UserDto user =  new UserDto();
        user.setBirthday(LocalDate.now());
        user.setPassword("test33");
        user.setEmail("yahiaoui@social.aston-ecole.com");
        user.setFullname("yahiaoui");
        user.setUsername("halim");

        this.signUpService.inscription (user,  "admin");
    }
}