package fr.sali.cantine.controleur;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class test {

    @GetMapping("/public")
   public  String  test  (){
        return "je suis  une page ";
    }



    @GetMapping("/private")
     public String  privateTest() {

          return  "am  i  private page  ";
    }


}
