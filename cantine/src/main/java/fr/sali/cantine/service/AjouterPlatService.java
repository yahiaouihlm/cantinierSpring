package fr.sali.cantine.service;


import fr.sali.cantine.dao.IPlatDao;
import fr.sali.cantine.dto.in.PlatDto;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.PlatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Service
public class AjouterPlatService {

    @Autowired
    private IPlatDao  platDao ;
    public PlatEntity ajouetPlat (PlatDto platDto ) throws  Exception {

         PlatEntity  plat =  new PlatEntity() ;
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
