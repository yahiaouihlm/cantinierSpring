package fr.sali.cantine.service.admin;


import fr.sali.cantine.dao.IMealDao;
import fr.sali.cantine.dao.IMenuDao;
import fr.sali.cantine.dto.in.MenuDto;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.MealEntity;
import fr.sali.cantine.entity.MenuEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
//Jhipster
@Service
public class MenuService {
    private static final Logger LOG = LogManager.getLogger();
       @Autowired
       IMealDao   mealDao ;
       @Autowired
       IMenuDao   menuDao ;
      public MenuEntity addMenu  (MenuDto menuDto) throws IllegalArgumentException, IOException {
           MenuEntity menu =  menuDto.toMenuEntity();
           List <MealEntity> meals =  getListMealByIDs( menuDto.getMealsIS()  );
           menu.setPlats(meals);

           /**************** les IMAGES mENU *******************/
          File image =  new File("src/main/resources/plat.png");
          var fis = new FileInputStream(image);
          ImageEntity imageEntity =  new ImageEntity();
          imageEntity.setImage(fis.readAllBytes());
          fis.close();
          menu.setImage(imageEntity);
          MenuService.LOG.info(" Je suis  avant  d'enregister le Menu ");
          return  menuDao.save(menu) ;
      }
        public List<MealEntity> getListMealByIDs (List <Integer> IDS) throws IllegalArgumentException {
            List <MealEntity> meals =  new LinkedList<>();
            for (Integer idmeal  :  IDS) {
               var meal =  mealDao.findById(idmeal) ;
               if (meal.isPresent()){
                   meals.add(meal.get()) ;
               }
            }
            if  (meals.isEmpty())
                throw  new IllegalArgumentException("you  Have Selected No valid Meal  to  your Menu ");
          return  meals ;
        }



        public  MenuEntity  getMenuInfo (Integer idmenu){
          if (idmenu == null || idmenu <  0 )
                throw  new IllegalArgumentException("Invalid Arguments ");
          var menu =  menuDao.findById(idmenu);
          if (!menu.isPresent())
                throw  new IllegalArgumentException("No Menu  Has been  found due to  ID");

          MenuService.LOG.info(" The Menu  Has Been  returned  ");
          return menu.get();
        }
}
