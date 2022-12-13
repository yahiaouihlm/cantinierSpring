package fr.sali.cantine.service.admin.meal;


import fr.sali.cantine.service.admin.MealService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RemoveMealTest {

    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    MealService   mealService;
      @Test
     void removeMealTest ()  {

          try {
              Integer  idMealToRemove = 5 ;
              mealService.removeMeal(idMealToRemove);
              RemoveMealTest.LOG.info("Meal Has Been Removed successfully ");
          }catch (Exception  e ) {
              RemoveMealTest.LOG.error(" ERROR Or Meal  doest not exists  :  " +  e.getMessage()); ;
          }

      }

}
