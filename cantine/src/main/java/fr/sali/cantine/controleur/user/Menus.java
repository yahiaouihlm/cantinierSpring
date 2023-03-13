package fr.sali.cantine.controleur.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.sali.cantine.dto.in.MenuDto;
import fr.sali.cantine.dto.out.MenuDtout;
import fr.sali.cantine.service.admin.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class Menus {

    @Autowired
    MenuService menuService;



    @GetMapping(value = "cantine/menus/removeOne/{id}")
    public  ResponseEntity<Object> removeMenu (@PathVariable("id") Integer  id ){
        try {
            System.out.println(id);
            this.menuService.removeMenuByid(id);
            return ResponseHandler.responseBuilder("Enregistrer", HttpStatus.OK, "Enregistrer");
        } catch (Exception e  ){
            System.out.println(e.getMessage());
            return ResponseHandler.responseBuilder("NOEnregistrer", HttpStatus.OK, "ERROR d'enregistrement");
        }
    }
    @PostMapping(value = "cantine/menus/addMenu", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addMenu(@ModelAttribute MenuDto menu) {
        //   System.out.println("je  suis  aussi  dans le controlleur est voici  meals" + mealsJSO);
        try {
              this.menuService.addMenu(menu);
            return ResponseHandler.responseBuilder("Enregistrer", HttpStatus.OK, "Enregistrer");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseHandler.responseBuilder("NOEnregistrer", HttpStatus.OK, "ERROR d'enregistrement");
        }
    }



    @GetMapping("cantine/menus/getMenus")
    public ResponseEntity<Object> getmenus (){
        try {

           List<MenuDtout> menus = this.menuService.getmenus();

            return ResponseHandler.responseBuilder("SENDED", HttpStatus.OK,menus );

        } catch (Exception e) {
            return ResponseHandler.responseBuilder("ERROR", HttpStatus.OK, "ERROR" );
        }
    }

    @GetMapping(value = "cantine/menus/getOne/{id}")
    public ResponseEntity<Object>  getMenu (@PathVariable("id") Integer id ) {
        try {
            var  menu =   this.menuService.getMenu(id);
            return ResponseHandler.responseBuilder("SENDED", HttpStatus.OK,menu );
        } catch (Exception e) {
            return ResponseHandler.responseBuilder("ERROR", HttpStatus.OK,"ERROR" );
        }
    }

}
