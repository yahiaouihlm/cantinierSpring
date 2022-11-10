package fr.sali.cantine.controleur;


import fr.sali.cantine.dto.in.UserDto;
import fr.sali.cantine.service.InscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/cantine/user/")
public class IncriptionControleur {
    private static final Logger LOG = LogManager.getLogger();
    @Autowired
    private InscriptionService serciceInscription ;

    @PostMapping("/register")
    public String inscription (@RequestParam("username") String username, @RequestParam("userfname") String userfname , @RequestParam("email") String email,
                               @RequestParam("password") String password , @RequestParam("birthday")LocalDate birthday ,  @RequestParam("phone") String phone){

        try {

            UserDto user  = new UserDto();
            user.setPassword(password);
            user.setEmail(email);
            user.setUserfname(userfname);
            user.setBirthday(birthday);
            user.setPhone(phone);
            user.setUsername(username);


            serciceInscription.inscription(user);
        } catch ( Exception e) {
            System.out.println(e);
          return  "KO" ;

        }

        return  "OK" ;
    }


}
