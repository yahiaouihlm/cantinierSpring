package fr.sali.cantine.service.user;


import fr.sali.cantine.dao.IMealDao;

import fr.sali.cantine.dao.IOrderDao;
import fr.sali.cantine.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealToOrderService {


    @Autowired
    IOrderDao orderDao ;
    @Autowired
    IMealDao mealDao ;
    public OrderEntity addMealToOrder  (Integer commandeID, Integer platID) throws  Exception{
        if (platID == null || platID < 0 || commandeID == null || commandeID < 0)
             throw new IllegalArgumentException("Invalid Arguments");

        var mealresult =  mealDao.findById(platID);
        var orderresult =  orderDao.findById(commandeID);

        if ( ! mealresult.isPresent()   ||  (! orderresult.isPresent()))
                          throw  new IllegalArgumentException(" Uknown User Or Meal ") ;

        var meals  =  mealresult.get();
        var order =  orderresult.get();
        order.addMeal(meals);

        return   orderDao.save(order);
    }

}
