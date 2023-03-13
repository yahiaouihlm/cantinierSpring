package fr.sali.cantine.service.admin;


import fr.sali.cantine.dao.IMealDao;
import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.dto.out.MealDtout;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.MealEntity;
import fr.sali.cantine.service.images.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {

     @Autowired
     private ImageService imageService;
    @Autowired
    private IMealDao mealDao ;


    public MealDtout getMealByid ( Integer id ) throws  Exception {
        if (id == null )
             throw  new IllegalArgumentException("INVALID ARGUMENT ");
        var meal = this.mealDao.findById(id);
        if (meal.isPresent()) {
            return  new MealDtout(meal.get());
        }

        throw  new RuntimeException("Uknwon  Meal ");
    }

    public List<MealDtout> getmeals (){
        List<MealEntity> meals =  this.mealDao.findAll();

        List<MealDtout>mealDtouts = new ArrayList<>();
        for ( MealEntity  meal : meals) {
            mealDtouts.add(new MealDtout(meal));
        }
        return  mealDtouts  ;
    }


    public MealEntity addMeal (MealtDto mealDto ) throws  Exception {
        MealEntity meal =  new MealEntity() ;
        meal = mealDto.toMeal() ;
        meal.setStatus(1);

        MultipartFile image =  mealDto.getImage();
        var imagename  =  this.imageService.uploadImage(image , "images/meals");

        ImageEntity imageEntity  =  new ImageEntity();
        imageEntity.setNameimage(imagename);


        meal.setImage(imageEntity);
        return  mealDao.save(meal);
    }


  public  void removeMeal  (Integer  idMeal) throws  Exception {
        if (idMeal == null  || idMeal  < 0)
               throw new  IllegalArgumentException(" Invalid Arguments");
        var mealToRemove = mealDao.findById(idMeal);
        if (mealToRemove.isEmpty())
                throw   new IllegalArgumentException(" Invalid Arguments ");

        var  meal =  mealToRemove.get();

        if  (meal.getMenus().size() > 0 ) //  vérifier que  aucun plat n'est en association  avec un  menu
            throw  new  RuntimeException("Impossible de supprimer ce plats");


        var imageName  = meal.getImage().getNameimage();

        if  (!this.imageService.removeImga(imageName, "images/meals" )) {
            System.out.println("je suis  la ducoup");
            throw new IllegalArgumentException("Invalid  image  name  ");

        }
        mealDao.deleteById(idMeal);

        return  ;
  }


  public MealEntity UpdateMeal (MealtDto mealDto , Integer idMeal) throws Exception {
      if (idMeal == null  || idMeal  < 0)
          throw new  IllegalArgumentException(" Invalid Arguments");

      var mealToUpdate = mealDao.findById(idMeal);

      if (mealToUpdate.isEmpty())
          throw   new IllegalArgumentException(" Invalid Arguments ");


      var mealtoupdate = mealToUpdate.get();

      if (mealDto.getCategorie() !=null)
                mealtoupdate.setCategorie(mealDto.getCategorie());
      if (mealDto.getDescription() != null)
                 mealtoupdate.setDescription(mealDto.getDescription());
      if (mealDto.getLabel()!=null)
          mealtoupdate.setLabel(mealDto.getLabel());
      if (mealDto.getLabel() !=null && mealDto.getPrixht().compareTo(BigDecimal.ZERO) >0)
          mealtoupdate.setPrixht(mealDto.getPrixht());
      if (mealDto.getQuantite() != null && mealDto.getQuantite()>0)
            mealtoupdate.setQuantite(mealDto.getQuantite());

      // si  y'a une image qui  à était mise a jour il  faut faire le update dans le depot
       if (mealDto.getImage()!=null){
           var  dimensional  =  mealtoupdate.getImage().getNameimage();
           var reimagine =  this.imageService.updateImage(dimensional,  mealDto.getImage(), "images/meals" );
           var image =  new ImageEntity();
           image.setNameimage(reimagine);
           mealtoupdate.setImage(image);
      }


      return  mealDao.save(mealtoupdate);
  }





}
