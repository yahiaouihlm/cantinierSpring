package fr.sali.cantine.controleur;


import fr.sali.cantine.dto.in.PlatDto;
import fr.sali.cantine.service.AddMealFromAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catine/admin/Ajouter")
public class AddMealFromAdminController {

    @Autowired
   private AddMealFromAdminService servicePlat ;

   @PostMapping("/Plat")
   public  String  ajouterPlay (PlatDto plat){
        try{
            servicePlat.ajouetPlat(plat) ;
        }catch ( Exception e){
             return e.getMessage();
        }
         return "Bien Enregister";
   }



}
