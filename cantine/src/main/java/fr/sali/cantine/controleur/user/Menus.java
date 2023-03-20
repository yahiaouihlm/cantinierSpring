package fr.sali.cantine.controleur.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.sali.cantine.dto.in.MenuDto;
import fr.sali.cantine.dto.out.MenuDtout;
import fr.sali.cantine.service.admin.MenuService;
import fr.sali.cantine.service.exception.ImagePathException;
import fr.sali.cantine.service.exception.MenuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class Menus {

    @Autowired
    MenuService menuService;

    @PostMapping(value = "cantine/menus/update/{idmenu}" , consumes = MULTIPART_FORM_DATA_VALUE)
    public  ResponseEntity<Object> updateMenu( @ModelAttribute MenuDto menu , @PathVariable("idmenu") Integer idmenu) throws MenuException, ImagePathException, IOException {
        this.menuService.updateMenu(menu , idmenu);
        return ResponseHandler.responseBuilder("SAVED", HttpStatus.OK, "SAVED");

    }

    @GetMapping(value = "cantine/menus/removeOne/{id}")
    public  ResponseEntity<Object> removeMenu (@PathVariable("id") Integer  id ) throws MenuException {
        this.menuService.removeMenuByid(id);
        return ResponseHandler.responseBuilder("REMOVED", HttpStatus.OK, "REMOVED");

    }
    @PostMapping(value = "cantine/menus/addMenu", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addMenu(@ModelAttribute MenuDto menu) throws MenuException, IOException, ImagePathException {
        this.menuService.addMenu(menu);
        return ResponseHandler.responseBuilder("ADDED", HttpStatus.OK, "ADDED");

    }



    @GetMapping("cantine/menus/getMenus")
    public ResponseEntity<Object> getmenus (){
        List<MenuDtout> menus = this.menuService.getmenus();
        return ResponseHandler.responseBuilder("SENDED", HttpStatus.OK,menus );
    }

    @GetMapping(value = "cantine/menus/getOne/{id}")
    public ResponseEntity<Object>  getMenu (@PathVariable("id") Integer id ) throws MenuException {
            var  menu =   this.menuService.getMenu(id);
            return ResponseHandler.responseBuilder("SENDED", HttpStatus.OK,menu );

    }

}
