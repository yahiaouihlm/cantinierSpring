package fr.sali.cantine.service.admin.meal;


import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.service.admin.MealService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateMealTest {
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    MealService mealService;

    @Test
    void updateMealService() {
        Integer mealID = 2;
        MealtDto mealtDto = new MealtDto();
        mealtDto.setQuantite(10);
        try {
            mealService.UpdateMeal(mealtDto, mealID);
            UpdateMealTest.LOG.info(" The Meal updated successfully");
        } catch (Exception e){
            UpdateMealTest.LOG.info("Error,  Or Unknown Meal  To Update " + e.getMessage());
        }

    }

}
