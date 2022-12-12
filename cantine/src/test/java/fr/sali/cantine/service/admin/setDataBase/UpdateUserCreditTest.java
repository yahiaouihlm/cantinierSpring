package fr.sali.cantine.service.admin.setDataBase;

import fr.sali.cantine.service.admin.UpdateUserCredit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class UpdateUserCreditTest {

    @Autowired
    UpdateUserCredit updateCreditService;
    @Test
    void updateUserCredit() {
         Integer id =    11 ;
        BigDecimal credit = new BigDecimal(4.9);
        updateCreditService.updateUserCredit(id , credit) ;
    }
}