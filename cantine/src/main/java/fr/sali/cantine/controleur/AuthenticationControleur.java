package fr.sali.cantine.controleur;


import fr.sali.cantine.dto.in.UserDto;
import fr.sali.cantine.service.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cantine/user")
public class AuthenticationControleur {

    @Autowired
    AuthentificationService authservice;

    @GetMapping("/authentification")
     public String  authentication  ( @RequestBody UserDto userinfo){
         try{
             authservice.authentification(userinfo) ;
         }catch( Exception e){
             return e.getMessage();
         }

        /**
         *   La création  de token  
         */


        return  "Bien  Identifié";
     };



}
