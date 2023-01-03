package fr.sali.cantine.service.admin;


import fr.sali.cantine.dao.IUserDao;
import fr.sali.cantine.dto.out.UserDtout;
import fr.sali.cantine.entity.RoleEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private IUserDao userDao ;
    private static final Logger LOG = LogManager.getLogger();



    public Boolean  deleteUser  (Integer  id ){
          if (id == null || id < 0) {
              throw    new   IllegalArgumentException (" Invalid  User ID ") ;
          }
          var  user =  userDao.findById(id);
          if (!user.isPresent()){
              throw   new IllegalArgumentException(" The User Doest Not Exists  ! ");
          }
          userDao.delete(user.get());
          return true ;
    }








    /**
     * @doc   : Vérifier La validité  de Id et renvoyer le ID Admin correspondant
     * @param id :  le Id De Ladmine
     * @return : Le Admin n'est pa
     */

    /*TODO
    *    Il faut Renvoyer  Un UserDTOUT Pas un  UserEntity
    * */

    public UserDtout getUserInformation ( Integer   id )  throws  IllegalArgumentException {
         if (id == null || id < 0)
              throw  new  IllegalArgumentException ("Invalid  Arguments ") ;

         LOG.debug(" id  Admin  Et Valide ");

           var result  =  userDao.findById(id);
        if (!result.isPresent())
             throw   new  IllegalArgumentException (" Invalid  Admin  ID  No Admin With  this Is Has Been  Found ") ;
           var  admin =  result.get();
        List<RoleEntity> roles = admin.getRoles();


        Boolean hasRoleAdmin =  false ;
        for (  RoleEntity role :  roles ) {
            if (role.getLibelle()  .equals("admin") ){
                hasRoleAdmin = true ;
            }

        }

        if (!hasRoleAdmin)
            throw  new  IllegalArgumentException("You Have Not Admin  ROle  ") ;
        LOG.trace("AdminName = { },  AdminFamilyname= { }, UserEmail = { } " , admin.getUsername(),  admin.getUserfname() ,  admin.getEmail() );
       System.out.println("AdminName = " +admin.getUsername()+ "   AdminFamilyname= "+admin.getUserfname() +" , UserEmail = "+admin.getEmail());
        return  null ;
    }
}
