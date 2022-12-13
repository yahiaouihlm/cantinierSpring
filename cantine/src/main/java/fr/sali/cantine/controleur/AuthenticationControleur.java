package fr.sali.cantine.controleur;


import fr.sali.cantine.dto.in.LoginDto;
import fr.sali.cantine.dto.in.UserDto;
import fr.sali.cantine.service.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cantine/user")
public class AuthenticationControleur {

    @Autowired
    AuthentificationService authservice;

    @PostMapping("/login")
     public String  authentication  ( @RequestBody LoginDto userinfo){
         try{
             authservice.authentification( userinfo) ;
         }catch( Exception e){
             return e.getMessage();
         }

        /**
         *   La création  de token d'authentification
         */


        return  "Bien  Identifié";
     };



}
