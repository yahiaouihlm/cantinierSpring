package fr.sali.cantine.controleur.user;


import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.dto.out.MealDtout;
import fr.sali.cantine.service.admin.MealService;
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
     public ResponseEntity<Object> updateMeal (@ModelAttribute MealtDto  meal,  @PathVariable ("id") Integer id ) {
         try {
            this.service .UpdateMeal(meal,  id  );
             return  ResponseHandler.responseBuilder("SUCCESS", HttpStatus.OK , "SUCCESS");
         } catch (  Exception  e ){
             return  ResponseHandler.responseBuilder("ERROR", HttpStatus.OK , "ERROR");
         }
     }

     /*
        meals/{id}f
        @RequestMapping("id") String myid
      */
    @GetMapping("/cantine/meals")
    public ResponseEntity<Object> getmelas(){
        try{
            List<MealDtout> meals =  new ArrayList<>( this.service.getmeals()) ;

            return  ResponseHandler.responseBuilder("SENDED", HttpStatus.OK , meals);
        }catch ( Exception e){
            return  ResponseHandler.responseBuilder("ERROR", HttpStatus.FORBIDDEN , "ERROR");
        }
    } //  end of class


    @GetMapping (value = "cantine/meals/getOne/{id}")
    public  ResponseEntity<Object> getMeal (@PathVariable("id") Integer id) {
          try{
              MealDtout mealByid = this.service.getMealByid(id);

              return ResponseHandler.responseBuilder("SENDED", HttpStatus.OK , mealByid);
          }catch ( Exception e) {

              return ResponseHandler.responseBuilder("ERROR", HttpStatus.OK , "ERROR");
          }
    }


    @GetMapping (value = "cantine/meals/removeOne/{id}")
    public  ResponseEntity <Object> removemeal (@PathVariable("id") Integer id  ){

         try {
             this.service.removeMeal(id);
             return ResponseHandler.responseBuilder("DELETED", HttpStatus.OK , "SUCCESS");
         }
         catch ( RuntimeException e ){
             return ResponseHandler.responseBuilder("CONSTRAINT", HttpStatus.OK , "ERROR");
         }
         catch ( Exception e ) {
             return ResponseHandler.responseBuilder("ERROR", HttpStatus.OK , "ERROR");
         }

    }

        @PostMapping (value = "/cantine/meals/add", consumes = MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity <Object> addmeal (@ModelAttribute MealtDto  meal){
            try {
                System.out.println(meal);
                service.addMeal(meal);
                return  ResponseHandler.responseBuilder("SUCCESS", HttpStatus.OK , "SUCCESS");
            } catch (Exception e) {
                System.out.println(e);
                return  ResponseHandler.responseBuilder("ERROR", HttpStatus.OK , "ERROR");
            }

        }


   /* @PostMapping(value = "/cantine/upload" )
    public  ResponseEntity<Object>uploadImage (@RequestParam("file") MultipartFile image ) {
        /*TODO  : mettre le path dans  le fichier de configuration

        var  path    =  "images/meals";

        try {
            this.imageServe.uploadImage(image ,  path);
            return  ResponseHandler.responseBuilder("upload successfully",  HttpStatus.OK, "success");
        } catch (Exception e) {
            return  ResponseHandler.responseBuilder("ERROR",  HttpStatus.OK, "ERROR");
        }


    } */



}
