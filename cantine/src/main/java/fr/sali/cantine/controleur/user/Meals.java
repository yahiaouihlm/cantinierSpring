package fr.sali.cantine.controleur.user;


import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.dto.out.MealDtout;
import fr.sali.cantine.service.admin.MealService;
import fr.sali.cantine.service.exception.ImagePathException;
import fr.sali.cantine.service.exception.MealException;
import fr.sali.cantine.service.exception.RemoveMealException;
import fr.sali.cantine.service.images.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class Meals {


     @Autowired
          private MealService service ;


     @Autowired
     private ImageService imageServe ;



     @PostMapping(value =  "/cantine/meals/update/{id}",  consumes = MULTIPART_FORM_DATA_VALUE)
     public ResponseEntity<Object> updateMeal (@ModelAttribute MealtDto  meal,  @PathVariable ("id") Integer id ) throws MealException, ImagePathException, IOException {
         this.service.UpdateMeal(meal,  id  );
         return  ResponseHandler.responseBuilder("SUCCESS", HttpStatus.OK ,"SUCCESS");
     }


    @GetMapping("/cantine/meals")
    public ResponseEntity<Object> getmelas(){
      List<MealDtout> meals =  new ArrayList<>( this.service.getmeals()) ;
      return  ResponseHandler.responseBuilder("SENDED", HttpStatus.OK , meals);

    } //  end of class


    @GetMapping (value = "cantine/meals/getOne/{id}")
    public  ResponseEntity<Object> getMeal (@PathVariable("id") Integer id) throws MealException {

    MealDtout mealByid = this.service.getMealByid(id);

    return ResponseHandler.responseBuilder("SENDED", HttpStatus.OK , mealByid);
    }


    @GetMapping (value = "cantine/meals/removeOne/{id}")
    public  ResponseEntity <Object> removemeal (@PathVariable("id") Integer id  ) throws RemoveMealException, MealException {

      this.service.removeMeal(id);
     return ResponseHandler.responseBuilder("DELETED", HttpStatus.OK , "SUCCESS");
    }



        @PostMapping (value = "/cantine/meals/add", consumes = MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity <Object> addmeal (@ModelAttribute MealtDto  meal) throws MealException, ImagePathException, IOException {
          service.addMeal(meal);
         return  ResponseHandler.responseBuilder("SUCCESS", HttpStatus.OK , "SUCCESS");
        }




}
