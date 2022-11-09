package fr.sali.cantine.service;

import fr.sali.cantine.dao.IUserDao;
import fr.sali.cantine.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class InscriptionService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    private IUserDao    userDao ;
    public UserEntity  Inscription ( D ){

    }


}
