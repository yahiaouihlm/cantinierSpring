package fr.sali.cantine.controleur;


import fr.sali.cantine.dto.in.UserDto;
import fr.sali.cantine.service.InscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/cantine/user")
public class IncriptionControleur {
    private static final Logger LOG = LogManager.getLogger();
    @Autowired
    private InscriptionService serciceInscription ;

    /*
     https://www.youtube.com/watch?v=J0LsMgXfgbU   <<== pour envoie des emails
     */

    @PostMapping("/register")
    public String inscription (@RequestBody UserDto userinfo){

          try {
            //serciceInscription.inscription(userinfo);
        } catch ( Exception e) {
            System.out.println(e);
          return  e.getMessage() ;

        }

          return  "ok";

    }




  }
