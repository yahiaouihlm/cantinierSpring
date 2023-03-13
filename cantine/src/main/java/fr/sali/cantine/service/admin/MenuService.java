package fr.sali.cantine.service.admin;


import fr.sali.cantine.dao.IMealDao;
import fr.sali.cantine.dao.IMenuDao;
import fr.sali.cantine.dto.in.MenuDto;
import fr.sali.cantine.dto.out.MenuDtout;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.MealEntity;
import fr.sali.cantine.entity.MenuEntity;
import fr.sali.cantine.service.images.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
       @Autowired
       ImageService imageService ;


       public void removeMenuByid  ( Integer id ) throws Exception  {
           if (id == null  || id  <  0) {
               throw   new   IllegalArgumentException("Invalid  Argument");
           }
           this.menuDao.deleteById(id);

       }
      public  List<MenuDtout> getmenus (){
          List<MenuEntity> menusEntity =  new ArrayList<>(this.menuDao.findAll());
          List<MenuDtout>  menuDtouts = menusEntity.stream()
                                                    .map (MenuDtout::new)
                                                    .toList();
          return  menuDtouts;
      }



       public MenuEntity addMenu  (MenuDto menuDto) throws Exception {

           MenuEntity menu =  menuDto.toMenuEntity();
           List<Integer> mealIDs = menuDto.getMealsIDS().stream().map((id)-> id.replaceAll("[^0-9]+", "")).map(
                   Integer::parseInt).toList();

           System.out.println("je suis avant   menudto ");
           List <MealEntity> meals =  getListMealByIDs( mealIDs );
           System.out.println("je suis apres  menudto ");
           menu.setPlats(meals);
           menu.setStatus(1);

           System.out.println("je suis  dans le traitement des images ");
           MultipartFile image  =  menuDto.getImage();
           var imagename=  this.imageService.uploadImage(image, "images/menus");

           ImageEntity imageEntity  =  new ImageEntity();
           imageEntity.setNameimage(imagename);
           LocalDate date = LocalDate.now();
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
           String formattedDate = date.format(formatter);
           menu.setJourassocier(formattedDate);
           menu.setImage(imageEntity);
           return  menuDao.save(menu) ;

       }




       public List<MealEntity> getListMealByIDs (List <Integer> IDS) throws IllegalArgumentException {
            List <MealEntity> meals =  new LinkedList<>();
            for (Integer idmeal  :  IDS) {
               var meal =  mealDao.findById(idmeal) ;
                meal.ifPresent(meals::add);
            }
            if  (meals.isEmpty())
                throw  new IllegalArgumentException("you  Have Selected No valid Meal  to  your Menu ");
          return  meals ;
        }


        public  MenuDtout getMenu (Integer idmenu )  throws  Exception{
          if (idmenu == null ||  idmenu < 0 )
              throw  new  IllegalArgumentException ("Invalid  argument ");
          var  menuEntity  =  this.menuDao.findById(idmenu);
            if (menuEntity.isEmpty()) {
                 throw  new IllegalArgumentException("Invalid  argument ");
            }

            return  new MenuDtout(menuEntity.get());
        }


}
