package fr.sali.cantine.controleur.user;


import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.dto.out.MealDtout;
import fr.sali.cantine.service.admin.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Meals {


     @Autowired
      private MealService service ;
    @GetMapping("/cantine/meals")
    public ResponseEntity<Object> getmelas(){
        try{
            List<MealDtout> meals =  new ArrayList<>( this.service.getmeals()) ;
            return  ResponseHandler.responseBuilder("LIST", HttpStatus.OK , meals);
        }catch ( Exception e){
            return  ResponseHandler.responseBuilder("NOTLIST", HttpStatus.FORBIDDEN , null);
        }
    } //  end of class


    @PostMapping ("/cantine/meals/add")
    public ResponseEntity <Object> addmeal (@RequestBody MealtDto  meal){

        try {
            System.out.println(meal);
            service.addMeal(meal);
            return  ResponseHandler.responseBuilder("NOTLIST", HttpStatus.OK ,new String("bien enregistrer"));
        } catch (Exception e) {
            System.out.println(e);
            return  ResponseHandler.responseBuilder("NOTLIST", HttpStatus.OK ,new String("ERROR d'enregistrement"));
        }

    }


}
