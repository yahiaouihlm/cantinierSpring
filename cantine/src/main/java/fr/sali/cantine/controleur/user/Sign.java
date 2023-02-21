package fr.sali.cantine.controleur.user;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import fr.sali.cantine.dto.in.LoginDto;
import fr.sali.cantine.dto.in.UserDto;
import fr.sali.cantine.dto.out.UserDtout;
import fr.sali.cantine.service.SignUpService;
import fr.sali.cantine.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@RestController
public class Sign {

        @Autowired
        SignUpService signUpService ;
        @Autowired
        UserInfoService  userInfoService ;
    /**
     *  TODO :     tester le Unitité des emails  dans la  base de donnes
     * @param
     * @return
     */

     @GetMapping ("/test")
     public String  test  (){
         SecurityContext context = SecurityContextHolder.getContext();
         System.out.println(context.getAuthentication().getDetails());
         System.out.println(context.getAuthentication().getPrincipal());
         System.out.println(context.getAuthentication().getDetails());
         return "ok";
     }
    @PostMapping("/cantine/user/signUP")
       public ResponseEntity<Object> signUP ( @RequestBody UserDto user ){
           try {
              signUpService.inscription(user , "user");
           } catch (Exception e) {
               // return  e.getMessage() ;
               return  ResponseHandler.responseBuilder("Not saved" , HttpStatus.OK , null ) ;
           }
           return  ResponseHandler.responseBuilder("saved  successfully" , HttpStatus.OK , null ) ;
       }


    /**
     *
     * @param login we  use  this  class cause
     * @return
     */
      @PostMapping("/cantine/user/myprofile")
      public ResponseEntity<Object> getUser (@RequestBody LoginDto login){
              UserDtout userinfo =null  ;
           try {
               userinfo  = this.userInfoService.getUser(login);

           }catch ( Exception  e){
               return  ResponseHandler.responseBuilder("user not found", HttpStatus.FORBIDDEN ,  null);
           }

           return  ResponseHandler.responseBuilder("user" , HttpStatus.OK , userinfo );

      }









    @PostMapping("/cantine/user/existemail")
         public ResponseEntity<Object>  existinEmail (@RequestBody LoginDto emailAsLogin ){
            var  email  =  emailAsLogin.getEmail() ;
             if (signUpService.existingUser(email)) {
                     return  ResponseHandler.responseBuilder("existingUser" , HttpStatus.OK  ,  "Exist");
             }
             else {
                 return  ResponseHandler.responseBuilder("notexistingUser" , HttpStatus.OK  ,  "Not Exist");
             }
        }


}

