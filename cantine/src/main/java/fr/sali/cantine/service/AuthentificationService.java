package fr.sali.cantine.service;


import fr.sali.cantine.dao.IUserDao;
import fr.sali.cantine.dto.in.UserDtoIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

    @Autowired
    private BCryptPasswordEncoder password ;

    @Autowired
    private IUserDao userDao ;

    /**
     *
     * @param usersignin  UserDTO ( Les information  transité via le controleur)
     * @return user id  si  utlisateur est trouvé dans la base de données
     * @throws IllegalArgumentException si  email  , login  sont incorrect
     */

    public Integer authentification (UserDtoIn usersignin) throws  IllegalArgumentException{
        usersignin.validateSignInInformation ();
        var userpassword  =  usersignin.getPassword();
        var userlogin    =  usersignin.getEmail();

        var user = userDao.findByEmail(userlogin);
        if (!user.isPresent()){
            throw  new IllegalArgumentException("Invalid password ") ;
        }
        if (!this.password.matches(userpassword , user.get().getPassword() )){
            throw  new IllegalArgumentException("Invalid password ") ;
        }


      return  user.get().getId();
    }




}
