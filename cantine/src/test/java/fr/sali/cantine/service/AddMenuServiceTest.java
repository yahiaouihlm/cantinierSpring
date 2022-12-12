package fr.sali.cantine.service;

import fr.sali.cantine.dto.in.MenuDto;
import fr.sali.cantine.service.admin.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List ;


@SpringBootTest
class AddMenuServiceTest {


    @Autowired
    MenuService serviceaddMenu;

    @Test
    void addMenuTest ()  throws  Exception {
        MenuDto menuDto =  new MenuDto() ;
        menuDto.setDescription("Un menu  tacos  coca  avec des beau   gout");
        menuDto.setPrixht( new BigDecimal(3.1) );
        menuDto.setLabel("Un Menu  Tacos ");
        menuDto.setStatus(1);
        List <Integer> ids  =  new LinkedList<>() ;
        ids.add(3);

        menuDto.setMealsIS(ids);

        serviceaddMenu.addMenu(menuDto) ;

    }


}