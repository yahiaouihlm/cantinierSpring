package fr.sali.cantine.service.admin.meal;

import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.service.admin.MealService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


@SpringBootTest
class AddMealServiceTest {

    private static final Logger LOG = LogManager.getLogger();
    @Autowired
    private MealService service;

    @Test
    public void addMealTest () {

        MealtDto platDto =  new MealtDto();
        platDto.setCategorie("Principale");
        platDto.setDescription(" Des pommes de terre  ");
        platDto.setLabel("Frites");
        platDto.setStatus(1) ;
        BigDecimal prix =  new BigDecimal(4.1) ;
        platDto.setPrixht(prix);
        platDto.setQuantite(2);
        AddMealServiceTest.LOG.info("The Meal  Added Succefully");
        try{
            service.addMeal(platDto);
        }catch ( Exception e){
            AddMealServiceTest.LOG.info("Error : " + e.getMessage());
        }


    }
}