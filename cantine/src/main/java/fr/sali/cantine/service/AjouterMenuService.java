package fr.sali.cantine.service;


import fr.sali.cantine.dao.IMenuDao;
import fr.sali.cantine.dto.in.MenuDto;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//Jhipster

@Service
public class AjouterMenuService {

       @Autowired
       IMenuDao   menuDao ;

      public MenuEntity ajouterMenu  (MenuDto menuDto) throws IllegalArgumentException, IOException {
             MenuEntity menu =  menuDto.toMenuEntity();


             /**************** les IMAGES mENU *******************/
          File image =  new File("src/main/resources/plat.png");
          var fis = new FileInputStream(image);
          ImageEntity imageEntity =  new ImageEntity();
          imageEntity.setImage(fis.readAllBytes());
          fis.close();

          menu.setImage(imageEntity);


           //menuDao.save(menu)
           return  null ;
        }
}
