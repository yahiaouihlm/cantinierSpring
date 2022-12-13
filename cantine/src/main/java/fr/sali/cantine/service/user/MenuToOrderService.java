package fr.sali.cantine.service.user;


import fr.sali.cantine.dao.IMenuDao;
import fr.sali.cantine.dao.IOrderDao;
import fr.sali.cantine.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuToOrderService {

    @Autowired
    IOrderDao orderDao ;

    @Autowired
    IMenuDao menuDao ;

    public OrderEntity addMealToOrder  (Integer commandeID, Integer menuID) throws  Exception{
        if (menuID == null || menuID < 0 || commandeID == null || commandeID < 0)
            throw new IllegalArgumentException("Invalid Arguments");

        var mealresult =  menuDao.findById(menuID);
        var orderresult =  orderDao.findById(commandeID);

        if ( ! mealresult.isPresent()   ||  (! orderresult.isPresent()))
            throw  new IllegalArgumentException(" Uknown User Or Meal ") ;

        var menu  =  mealresult.get();
        var order =  orderresult.get();
        order.addMenu(menu);

        return   orderDao.save(order);
    }


}
