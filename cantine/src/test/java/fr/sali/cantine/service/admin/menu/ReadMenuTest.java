package fr.sali.cantine.service.admin.menu;


import fr.sali.cantine.entity.MealEntity;
import fr.sali.cantine.entity.MenuEntity;
import fr.sali.cantine.service.admin.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadMenuTest {
    private static final Logger LOG = LogManager.getLogger();
     @Autowired
    private MenuService menuService;
    @Test
    void getMenu () {
         try {
             MenuEntity menu =  menuService.getMenuInfo(3);
             ReadMenuTest.LOG.info("Menu name  = " + menu.getLabel());
             for (MealEntity meal : menu.getPlats() ) {
                 ReadMenuTest.LOG.info(" plat : + " + meal.getLabel() + " \n " );
             }
         }catch ( Exception  e){
             ReadMenuTest.LOG.info(" --- ERROR --- :  " + e.getMessage());
         }
    }
}
