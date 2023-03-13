package fr.sali.cantine.controleur.user;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import fr.sali.cantine.dto.in.LoginDto;
import fr.sali.cantine.dto.in.MealtDto;
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

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

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


    @PostMapping(value = "/cantine/user/signUP",  consumes = MULTIPART_FORM_DATA_VALUE)
       public ResponseEntity<Object> signUP (@ModelAttribute UserDto user ){
           try {
               signUpService.inscription(user , "user");
               System.out.println(user);
           } catch (Exception e) {
               // return  e.getMessage() ;
               System.out.println(e.getMessage());
               return  ResponseHandler.responseBuilder("SECCESS" , HttpStatus.OK , "SECCESS" ) ;
           }
           return  ResponseHandler.responseBuilder("ERROR" , HttpStatus.OK , "ERROR" ) ;
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

