package fr.sali.cantine.service;

import fr.sali.cantine.dto.in.PlatDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AjouterPlatServiceTest {

    @Autowired
    private AjouterPlatService service;

    @Test
    public void ajouterPlatTest () {

        PlatDto platDto =  new PlatDto ();
        platDto.setCategorie("Boisson");
        platDto.setDescription("Une boisson   sucré ");
        platDto.setLabel("Coucacola");
        platDto.setStatus(1) ;
        BigDecimal prix =  new BigDecimal(3.1) ;
        platDto.setPrixht(prix);
        try{
            service.ajouetPlat(platDto);
        }catch ( Exception e){
            System.out.println(e);
            System.exit(1);
        }


    }
}