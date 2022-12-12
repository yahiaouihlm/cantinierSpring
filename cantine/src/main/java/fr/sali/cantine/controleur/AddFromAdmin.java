package fr.sali.cantine.controleur;

import fr.sali.cantine.dto.in.MealtDto;
import fr.sali.cantine.dto.in.MenuDto;
import fr.sali.cantine.service.admin.MealService;
import fr.sali.cantine.service.admin.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class AddFromAdmin {

    @RestController
    @RequestMapping("/catine/admin/Add")
    public class AddMealFromAdminController {

        @Autowired
        private MealService servicePlat ;

        @Autowired
        private MenuService serviceMenu ;

        @PostMapping("/Meal")
        public  String  addMeal (MealtDto plat){
            try{
                servicePlat.addMeal(plat) ;
            }catch ( Exception e){
                return e.getMessage();
            }
            return "Bien Enregister";
        }


        @PostMapping("/Menu")
        public  String  AddMenu (MenuDto menuDto){
            try{
                serviceMenu.addMenu (menuDto) ;
            }catch ( Exception e){
                return e.getMessage();
            }
            return "Bien Enregister";
        }




    }
}
