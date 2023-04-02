package fr.sali.cantine.controleur.user;


import fr.sali.cantine.dto.in.LoginDto;
import fr.sali.cantine.dto.in.UserValidationCode;
import fr.sali.cantine.service.ForgetPasswordService;
import fr.sali.cantine.service.exception.ExpiredCode;
import fr.sali.cantine.service.exception.InvalidUserCode;
import fr.sali.cantine.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgetPassword {


    @Autowired
    ForgetPasswordService forgetPassword ;
@GetMapping("cantine/users/forgetpassword")
    public ResponseEntity<Object> SendMailToupdatePassword (@RequestBody LoginDto login) throws UserNotFoundException {
         this.forgetPassword.sendMailToUpdatePassword(login.getEmail());
       return ResponseHandler.responseBuilder("SENDED", HttpStatus.OK, "SENDED");
      }

      @GetMapping("cantine/users/forgetpassword/checkCode")
      public  ResponseEntity<Object> checkUserCode (@RequestBody UserValidationCode userinfo ) throws UserNotFoundException, InvalidUserCode, ExpiredCode {
         this.forgetPassword.checkCodeValidator(userinfo.getEmail(),  userinfo.getCode());
          return ResponseHandler.responseBuilder("FINDED", HttpStatus.OK, "FINDED");
      }


}
