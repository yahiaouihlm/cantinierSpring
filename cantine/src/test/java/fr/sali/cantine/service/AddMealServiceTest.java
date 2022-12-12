package fr.sali.cantine.service;

import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.service.admin.MealService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


@SpringBootTest
class AddMealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void addMealTest () {

        MealtDto platDto =  new MealtDto();
        platDto.setCategorie("Boisson");
        platDto.setDescription("Une boisson   sucré ");
        platDto.setLabel("Coucacola");
        platDto.setStatus(1) ;
        BigDecimal prix =  new BigDecimal(3.1) ;
        platDto.setPrixht(prix);
        platDto.setQuantite(4);
        try{
            service.addMeal(platDto);
        }catch ( Exception e){
            System.out.println(e);
            System.exit(1);
        }


    }
}