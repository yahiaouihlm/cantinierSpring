package fr.sali.cantine.springsecurity.springsecurityuser;

import fr.sali.cantine.controleur.user.ResponseHandler;
import fr.sali.cantine.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;


@Component
public class CantineUserDailsService implements UserDetailsService {
    @Autowired
    IUserDao iUserDao ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var  userOptional =  iUserDao.findByEmail(username);
        if (!userOptional.isPresent()) {
            throw   new UsernameNotFoundException("this  User Doest not exist");
        }
        var user =  userOptional.get();
        return new CantineUserDetails(user);
    }
}
