package fr.sali.cantine.service.admin;


import fr.sali.cantine.dao.IMealDao;
import fr.sali.cantine.dao.IMenuDao;
import fr.sali.cantine.dto.in.MenuDto;
import fr.sali.cantine.dto.out.MenuDtout;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.MealEntity;
import fr.sali.cantine.entity.MenuEntity;
import fr.sali.cantine.service.exception.ImagePathException;
import fr.sali.cantine.service.exception.MenuException;
import fr.sali.cantine.service.images.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@Service
public class MenuService {
    private static final Logger LOG = LogManager.getLogger();
       @Autowired
       IMealDao   mealDao ;
       @Autowired
       IMenuDao   menuDao ;
       @Autowired
       ImageService imageService ;

       public  MenuService (){}
       public MenuEntity updateMenu (MenuDto menudto ,  Integer idmenu) throws MenuException, ImagePathException, IOException {
           if (idmenu==null) {
               MenuService.LOG.debug(" NUll id  in  updateMenu ");
               throw   new MenuException("Invalid  Menu ID ");
           }
           var menuinDBOpt =  this.menuDao.findById(idmenu);
           if (!menuinDBOpt.isPresent()) {
               MenuService.LOG.debug("  Invalid Menu ID == {} ",  idmenu);
               throw   new MenuException("Invalid  Menu ID");
           }
           var menuinDB =  menuinDBOpt.get();

           if (menudto.getLabel()== null || menudto.getLabel().length()<=0 || menudto.getLabel().length()> 100 )
               throw new MenuException("Invalid Label of Menu ");

           if  (menudto.getDescription() == null || menudto.getDescription().length()<=0  )
               throw new MenuException("Invalid Label of Menu ");

           if (menudto.getPrixht() == null || menudto.getPrixht().compareTo(BigDecimal.ZERO) < 0 )
               throw  new MenuException("Invalid Price Of menu ");

           if (menudto.getQuantite() == null || menudto.getQuantite() <0 )
               throw  new MenuException("Invalid  Menu  Quantity ");

           if (menudto.getImage() != null ){
               var  oldImageName  =  menuinDB.getImage().getNameimage();
               var reimagine =  this.imageService.updateImage(oldImageName,  menudto.getImage(), "images/meals" );
               var image =  new ImageEntity();
               image.setNameimage(reimagine);
               menuinDB.setImage(image);
           }
           menuinDB.setQuantite(menudto.getQuantite());
           menuinDB.setJourassocier(LocalDate.now().toString()) ;
           menuinDB.setPrixht(menudto.getPrixht());
           menuinDB.setStatus(1);
           menuinDB.setLabel(menudto.getLabel());
           menuinDB.setDescription(menudto.getDescription());

           List<Integer> mealIDs = menudto.getMealsIDS().stream().map((id)-> id.replaceAll("[^0-9]+", "")).map(
                   Integer::parseInt).toList();
           if (mealIDs.size()< 2)
               throw  new MenuException("Un nombre de Plats insufusant ");

           List <MealEntity> meals =  getListMealByIDs( mealIDs );
           if (meals.size() < 2 ){
               throw  new  MenuException("Invalid  Meal  IDs ");
           }
           menuinDB.setPlats(meals);


           return this.menuDao.save(menuinDB);
       }
       public void removeMenuByid  ( Integer id ) throws MenuException  {
           if (id == null  || id  <  0) {
               MenuService.LOG.debug(" Null  Argument With  in removeMenuByid ");
               throw   new   MenuException("Invalid  Argument To Remove Menu ");
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



      public MenuEntity addMenu  (MenuDto menuDto) throws MenuException, IOException, ImagePathException {

           MenuEntity menu =  menuDto.toMenuEntity();
           List<Integer> mealIDs = menuDto.getMealsIDS().stream().map((id)-> id.replaceAll("[^0-9]+", "")).map(
                   Integer::parseInt).toList();

           if (mealIDs.size()< 2){
               throw  new MenuException(" No Enough Meal Number ");
           }


           List <MealEntity> meals =  getListMealByIDs( mealIDs );

           if (meals.size() < 2 ){
               throw  new  IllegalArgumentException("Invalid  Meal  IDs ");
           }

           menu.setPlats(meals);
           menu.setStatus(1);


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




       public List<MealEntity> getListMealByIDs (List <Integer> IDS) throws MenuException {
            List <MealEntity> meals =  new LinkedList<>();
            for (Integer idmeal  :  IDS) {
               var meal =  mealDao.findById(idmeal) ;
                meal.ifPresent(meals::add);
            }
            if  (meals.isEmpty()){
                MenuService.LOG.debug("No Meal Has Been  found in getListByID");
                throw  new MenuException("No Meal  Has Been  Found ");
            }

          return  meals ;
        }


        public  MenuDtout getMenu (Integer idmenu )  throws  MenuException{
          if (idmenu == null ||  idmenu < 0 ){
              MenuService.LOG.debug(" Invalid Menu  ID in  get Menu { ID : {} } in geteMenu",idmenu);
              throw new  MenuException("Invalid Menu ID") ;
          }

          var  menuEntity  =  this.menuDao.findById(idmenu);
            if (menuEntity.isEmpty()) {
                MenuService.LOG.debug("No Menu  Has Been Found With { ID {} } in  geteMenu", idmenu);
                throw  new MenuException("No Menu Has Been Found ");
              }

            return  new MenuDtout(menuEntity.get());
        }


}
