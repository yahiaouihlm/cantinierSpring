package fr.sali.cantine.service.admin;


import fr.sali.cantine.dao.IUserDao;
import fr.sali.cantine.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UpdateUserCredit {

    @Autowired
    IUserDao userDao ;
     /* TODO
        ** Modifier La Base de Donnée pour une Valeur Par Defaut 0
        Il faut Envoyer Un Email  A utilisateur Pour Confirmer et Mettre AU Courant Des Changement Dans  son  Crédit
      */
    public UserEntity updateUserCredit  (Integer id , BigDecimal  newCredit) throws  IllegalArgumentException{
         if (id == null || id < 0  ||  newCredit.compareTo(BigDecimal.ZERO) < 0 )
                     throw  new IllegalArgumentException("Invalid Arguments ");
         var result  =  userDao.findById(id) ;
         if (!result.isPresent()) {
             throw  new IllegalArgumentException(" Uknown User ");
         }

         var user =  result.get() ;
         user.setCredit(newCredit);

        return  userDao.save(user);
    }
}
