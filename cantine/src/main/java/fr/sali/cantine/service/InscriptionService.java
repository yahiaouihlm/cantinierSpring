package fr.sali.cantine.service;

import fr.sali.cantine.dao.IUserDao;
import fr.sali.cantine.dto.in.UserDtoIn;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;


@Service
public class InscriptionService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private IUserDao    userDao ;

    public UserEntity inscription(UserDtoIn userdto)   throws  Exception{
        UserEntity   user =  userdto.toEntity();
        String password =  userdto.getPassword();
        String  cryptedpassword = encoder.encode(password);
        user.setPassword(cryptedpassword);

         File image =  new File("src/main/resources/photoprofile.png");
         var fis = new FileInputStream(image);

         ImageEntity imageEntity =  new ImageEntity();
         imageEntity.setImage(fis.readAllBytes());
         fis.close();
         user.setImage(imageEntity);


        return userDao.save(user);

    }


}
