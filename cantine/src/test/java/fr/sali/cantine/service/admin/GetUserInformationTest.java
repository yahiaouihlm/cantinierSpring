package fr.sali.cantine.service.admin;

import fr.sali.cantine.service.admin.GetUserInformation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class GetUserInformationTest {

     @Autowired
     GetUserInformation getInfoService ;

    @Test
    void  getAdminInformationServiceTest () {

         Integer  idAdmin = 12;
         getInfoService.getUserInformation(idAdmin);

    }
}