package fr.sali.cantine.service.admin;


import fr.sali.cantine.dao.IMealDao;
import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.dto.out.MealDtout;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.MealEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    @Autowired
    private IMealDao mealDao ;
    public MealEntity addMeal (MealtDto mealDto ) throws  Exception {
         MealEntity meal =  new MealEntity() ;
        meal = mealDto.toMeal() ;
        /******************* Mettre l'image par défaut ********************************/
        File image =  new File("src/main/resources/plat.png");
        var fis = new FileInputStream(image);
        ImageEntity imageEntity =  new ImageEntity();
        imageEntity.setImage(fis.readAllBytes());
        fis.close();
        meal.setImage(imageEntity);

         return  mealDao.save(meal);
    }


  public  MealEntity removeMeal  (Integer  idMeal) throws  Exception {
        if (idMeal == null  || idMeal  < 0)
               throw new  IllegalArgumentException(" Invalid Arguments");

        var mealToRemove = mealDao.findById(idMeal);
        if ( !mealToRemove.isPresent())
                throw   new IllegalArgumentException(" Invalid Arguments ");
        mealDao.deleteById(idMeal);

        return mealToRemove.get() ;
  }


  public MealEntity UpdateMeal (MealtDto mealDto , Integer idMeal){
      if (idMeal == null  || idMeal  < 0)
          throw new  IllegalArgumentException(" Invalid Arguments");

      var mealToUpdate = mealDao.findById(idMeal);

      if ( !mealToUpdate.isPresent())
          throw   new IllegalArgumentException(" Invalid Arguments ");


      var mealtoupdate = mealToUpdate.get();

      if (mealDto.getImage() != null)
            mealtoupdate.setImage(mealDto.getImage());
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
      return  mealDao.save(mealtoupdate);
  }


    public List<MealDtout> getmeals (){
        List<MealEntity> meals =  this.mealDao.findAll();
        List<MealDtout>mealDtouts = new ArrayList<>();
        for ( MealEntity  meal : meals) {
             mealDtouts.add(new MealDtout(meal));
        }
        return  mealDtouts  ;
    }
}
