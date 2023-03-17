package fr.sali.cantine.service.admin;


import fr.sali.cantine.dao.IMealDao;
import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.dto.out.MealDtout;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.MealEntity;
import fr.sali.cantine.service.exception.ImagePathException;
import fr.sali.cantine.service.exception.MealException;
import fr.sali.cantine.service.exception.RemoveMealException;
import fr.sali.cantine.service.images.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {
    private static final Logger LOG = LogManager.getLogger();
     @Autowired
     private ImageService imageService;
    @Autowired
    private IMealDao mealDao ;


    public MealDtout getMealByid ( Integer id ) throws  MealException {
        if (id == null )
             throw  new MealException("  Invalid Meal ID  in getMealByid : ID can not Be NuLL ");
        var meal = this.mealDao.findById(id);
        if (meal.isPresent()) {
            return  new MealDtout(meal.get());
        }

        throw  new MealException("No Meal Has Been Found  In getMealByid  With : "+ id +"ID ");
    }

    public List<MealDtout> getmeals (){
        List<MealEntity> meals =  this.mealDao.findAll();

        List<MealDtout>mealDtouts = new ArrayList<>();
        for ( MealEntity  meal : meals) {
            mealDtouts.add(new MealDtout(meal));
        }
        return  mealDtouts  ;
    }


    public MealEntity addMeal (MealtDto mealDto ) throws ImagePathException, MealException, IOException {
        MealEntity meal =  new MealEntity() ;
        meal = mealDto.toMeal() ;
        meal.setStatus(1);

        MultipartFile image =  mealDto.getImage();
        var imagename  =  this.imageService.uploadImage(image , "images/meals");

        ImageEntity imageEntity  =  new ImageEntity();
        imageEntity.setNameimage(imagename);

        meal.setImage(imageEntity);

        var newmMeal =  mealDao.save(meal);

       return  newmMeal;
    }


  public  void removeMeal  (Integer  idMeal) throws MealException, RemoveMealException {
        if (idMeal == null  || idMeal  < 0)
               throw new MealException("Invalid Meal ID  TO  Remove  { ID : " + idMeal + "}");
        var mealToRemove = mealDao.findById(idMeal);
        if (mealToRemove.isEmpty())
            throw new MealException("Meal Not Found To Remove { ID : "+ idMeal + "}" );

      var  meal =  mealToRemove.get();

        if  (meal.getMenus().size() > 0 ) //  vérifier que  aucun plat n'est en association  avec un  menu
            throw  new RemoveMealException("This Meal Can Not be Removed Because Its founds in Other Meal  { label :" + meal.getLabel() + " id: "+meal.getIdplat()+"}");


        var imageName  = meal.getImage().getNameimage();

        if  (!this.imageService.removeImga(imageName, "images/meals" )) {

            throw new MealException (" Image Can Not Be Removed ");

        }
        mealDao.deleteById(idMeal);
        MealService.LOG.info(" The Meal Successfuly Deleted { }", meal.getLabel());
        return  ;
  }


  public MealEntity UpdateMeal (MealtDto mealDto , Integer idMeal) throws MealException, ImagePathException, IOException {
      if (idMeal == null  || idMeal  < 0)
          throw new MealException("Invalid Meal ID  TO  update  { ID : " + idMeal + "}");

      var mealToUpdate = mealDao.findById(idMeal);

      if (mealToUpdate.isPresent())
          throw   new MealException(" Invalid Arguments No Meal Found With  { ID :" + idMeal + " } ");


      var mealtoupdate = mealToUpdate.get();

      if (mealDto.getCategorie() == null)
                 throw  new MealException(" Categorie Must Not Be NULL To Update Meal ");
      if (mealDto.getDescription() == null)
          throw  new MealException(" Description Must Not Be NULL To Update Meal ");
      if (mealDto.getLabel()!=null)
          throw  new MealException(" Label Must Not Be NULL To Update Meal ");
      if (mealDto.getPrixht() !=null && mealDto.getPrixht().compareTo(BigDecimal.ZERO) >0)
          throw  new MealException(" Prixht Must Not Be NULL To Update Meal ");
      if (mealDto.getQuantite() != null && mealDto.getQuantite()>0)
          throw  new MealException(" Quantite Must Not Be NULL To Update Meal ");

      mealtoupdate.setQuantite(mealDto.getQuantite());
      mealtoupdate.setPrixht(mealDto.getPrixht());
      mealtoupdate.setLabel(mealDto.getLabel());
      mealtoupdate.setDescription(mealDto.getDescription());
      mealtoupdate.setCategorie(mealDto.getCategorie());
      // si  y'a une image qui  à était mise a jour il  faut faire le update dans le depot
       if (mealDto.getImage()!=null){
           var  oldImageName  =  mealtoupdate.getImage().getNameimage();
           var reimagine =  this.imageService.updateImage(oldImageName,  mealDto.getImage(), "images/meals" );
           var image =  new ImageEntity();
           image.setNameimage(reimagine);
           mealtoupdate.setImage(image);
      }


      return  mealDao.save(mealtoupdate);
  }





}
