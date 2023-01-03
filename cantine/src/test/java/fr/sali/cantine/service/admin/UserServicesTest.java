package fr.sali.cantine.service.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServicesTest {

    @Autowired
    UserServices  userServices;
    @Test
    public  void deleteUserTest (){
         var test =  userServices.deleteUser(1);
        System.out.println(test);
    }
}