package fr.sali.cantine.controleur;


import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.service.admin.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catine/admin/Ajouter")
public class AddMealFromAdminController {

    @Autowired
   private MealService servicePlat ;

   @PostMapping("/Plat")
   public  String  ajouterPlay (MealtDto plat){
        try{
            servicePlat.addMeal(plat) ;
        }catch ( Exception e){
             return e.getMessage();
        }
         return "Bien Enregister";
   }



}
