package fr.sali.cantine.controleur.user;


import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.dto.out.MealDtout;
import fr.sali.cantine.service.admin.MealService;
import fr.sali.cantine.service.images.ImageServe;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class Meals {


     @Autowired
          private MealService service ;


     @Autowired
     private ImageServe imageServe ;

     @PostMapping(value = "/cantine/upload" )
     public  ResponseEntity<Object>uploadImage (@RequestParam("file") MultipartFile image ) {
         /*TODO  : mettre le path dans  le fichier de configuration */

         var  path    =  "images/meals";

         try {
             this.imageServe.uploadImage(image ,  path);
            return  ResponseHandler.responseBuilder("upload successfully",  HttpStatus.OK, "success");
         } catch (Exception e) {
             return  ResponseHandler.responseBuilder("ERROR",  HttpStatus.OK, "ERROR");
         }


     }





     // pour renvoyer l'image  !
     @GetMapping(value = "/cantine/download/{image}")
     public   ResponseEntity<InputStreamResource>  getImage ( @PathVariable(value="image") String image ){
         /*TODO  : mettre le path dans  le fichier de configuration */
         var  path  = "images/meals";
         try {
             MediaType contentType =  image.substring(image.lastIndexOf('.')).equals(".png") ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;
             InputStream input = this.imageServe.downloadImage(image , path );
             return ResponseEntity.ok()
                     .contentType(contentType)
                     .body(new InputStreamResource(input));
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
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

            return  ResponseHandler.responseBuilder("LIST", HttpStatus.OK , meals);
        }catch ( Exception e){
            return  ResponseHandler.responseBuilder("NOTLIST", HttpStatus.FORBIDDEN , null);
        }
    } //  end of class


    @GetMapping (value = "cantine/meals/getOne/{id}")
    public  ResponseEntity<Object> getMeal (@PathVariable("id") Integer id) {
          try{
              MealDtout mealByid = this.service.getMealByid(id);
              System.out.println(mealByid);
              return ResponseHandler.responseBuilder("FOUND", HttpStatus.OK , mealByid);
          }catch ( Exception e) {

              return ResponseHandler.responseBuilder("NOT FOUND", HttpStatus.OK , "ERROR");
          }
    }


    @GetMapping (value = "cantine/meals/removeOne/{id}")
    public  ResponseEntity <Object> removemeal (@PathVariable("id") Integer id  ){

         try {
             this.service.removeMeal(id);
             return ResponseHandler.responseBuilder("DELETED", HttpStatus.OK , "SUCCESS");
         }catch ( Exception e ) {
             return ResponseHandler.responseBuilder("NOT DELETED", HttpStatus.OK , "ERROR");
         }

    }

    @PostMapping (value = "/cantine/meals/add", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <Object> addmeal (@ModelAttribute MealtDto  meal){
        System.out.println("je suis  la ");
        try {
            System.out.println(meal);
            service.addMeal(meal);
            return  ResponseHandler.responseBuilder("NOTLIST", HttpStatus.OK , "bien enregistrer");
        } catch (Exception e) {
            System.out.println(e);
            return  ResponseHandler.responseBuilder("NOTLIST", HttpStatus.OK , "ERROR d'enregistrement");
        }

    }


}
