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
     public String uploadImage (@RequestParam("file") MultipartFile image ) throws Exception {


           var  name  = image.getOriginalFilename();

           name = new Date().getTime() + name ;
           var  path    =  "images/" + name  ;

           File file  =  new File (path);
           file.createNewFile();

          image.transferTo(Path.of(file.getPath()));

         System.out.println(image.getSize());
          // return image.getName();



           return  name;
     }





     // pour renvoyer l'image  !
     @GetMapping(value = "/cantine/download/{image}")
     public   ResponseEntity<InputStreamResource>  getImage ( @PathVariable(value="image") String image ) throws FileNotFoundException {

        //  File  file  =  imageServe.uploadImage(image,"src/main/resources/images/meals");
         MediaType contentType =  image.substring(image.lastIndexOf('.')).equals(".png") ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;

         InputStream input =  new   FileInputStream("images/"+image);

         return ResponseEntity.ok()
                 .contentType(contentType)
                 .body(new InputStreamResource(input));


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
