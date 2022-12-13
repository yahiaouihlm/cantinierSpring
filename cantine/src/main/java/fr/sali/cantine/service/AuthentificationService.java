package fr.sali.cantine.service;


import fr.sali.cantine.dao.IUserDao;
import fr.sali.cantine.dto.in.LoginDto;
import fr.sali.cantine.dto.in.UserDto;
import fr.sali.cantine.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

    @Autowired
    private BCryptPasswordEncoder password ;

    @Autowired
    private IUserDao userDao ;



     /*
     *
     * @param usersignin  UserDTO ( Les information  transité via le controleur)
     * @return user id  si  utlisateur est trouvé dans la base de données
     * @throws IllegalArgumentException si  email  , login  sont incorrect
     */

    public UserEntity authentification (LoginDto usersignin) throws  IllegalArgumentException{
        usersignin.validate ();
        var userpassword  =  usersignin.getPassword();
        var userlogin    =  usersignin.getEmail();

        var user = userDao.findByEmail(userlogin);
        if (!user.isPresent()){
            throw  new IllegalArgumentException("Invalid password OR Email") ;
        }
        if (!this.password.matches(userpassword , user.get().getPassword() )){
            throw  new IllegalArgumentException("Invalid password OR Email ") ;
        }


      return  user.get();
    }



}
