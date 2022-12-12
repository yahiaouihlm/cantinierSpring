package fr.sali.cantine.service.admin;


import fr.sali.cantine.dao.IMealDao;
import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.MealEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Service
public class MealService {

    @Autowired
    private IMealDao platDao ;
    public MealEntity addMeal (MealtDto platDto ) throws  Exception {

         MealEntity plat =  new MealEntity() ;
         plat = platDto.toPlat() ;


        /******************* Mettre l'image par défaut ********************************/
        File image =  new File("src/main/resources/plat.png");
        var fis = new FileInputStream(image);
        ImageEntity imageEntity =  new ImageEntity();
        imageEntity.setImage(fis.readAllBytes());
        fis.close();
        plat.setImage(imageEntity);

         return  platDao.save(plat);
    }

}
