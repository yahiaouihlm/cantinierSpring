package fr.sali.cantine.controleur.user;


import fr.sali.cantine.dto.in.UserDto;
import fr.sali.cantine.dto.out.UserDtout;
import fr.sali.cantine.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class Sign {

        @Autowired
        SignUpService signUpService ;

       @PostMapping("/cantine/user/signUP")
       public ResponseEntity<Object> signUP (UserDto user ){
           System.out.println("vous etes bien  dans  le controleur ");
           System.out.println(user.getUsername());
           try {
           //    signUpService.inscription(user , "user");
           } catch (Exception e) {
               // return  e.getMessage() ;
               return  null  ;
           }
           return  ResponseHandler.responseBuilder("saved  successfully" , HttpStatus.OK , null ) ;
       }

}

