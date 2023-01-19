package fr.sali.cantine.service.user;


import fr.sali.cantine.dao.IUserDao;
import fr.sali.cantine.dto.in.LoginDto;
import fr.sali.cantine.dto.in.UserDto;
import fr.sali.cantine.dto.out.UserDtout;
import fr.sali.cantine.entity.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {
     private static final Logger LOG = LogManager.getLogger();

     @Autowired
     private IUserDao userDao ;
     /**
      *  La méthode permet uniquement de modifier les information qui nécessite un  vérification ( donc update password ,  email  dans une autre fonction )
      * @param userDto :  Les User INformation
      */

      //  Il manque le update des images
     public UserEntity  updateUserInformation ( UserDto userDto ) throws  IllegalArgumentException{
           userDto.checkNewUserInformation();
           var userID  =  userDto.getId();
           var oldUser =  userDao.findById(userID);
           if (!oldUser.isPresent()){
                LOG.debug(" Uknown  User ID Not FOund  !! ");
                throw   new IllegalArgumentException("Invalid User ID") ;
           }
            var result =  oldUser.get() ;
             result.setBirthday(userDto.getBirthday() );
             result.setUserfname(userDto.getUserfname());
             result.setUsername(userDto.getUsername());
             if (userDto.getPhone() != null)
                  result.setPhone(userDto.getPhone());

         LOG.debug("User Information  Has been  updated  ! ");

         var updatedUser  =  userDao.save(result) ;
         LOG.debug("User Information  Has been  savec successfully  ! ");
         return  updatedUser;
     }


     public  UserDtout getUser (LoginDto login ) throws  IllegalArgumentException {
       var  email = login.getEmail() ;
       var  user  =  this.userDao.findByEmail(email);
         if (!user.isPresent()) {
             throw  new   IllegalArgumentException("user does not exist") ;
         }

        return new UserDtout(user.get()) ;
     }


} //  end  of class
