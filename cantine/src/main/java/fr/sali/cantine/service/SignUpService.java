package fr.sali.cantine.service;

import fr.sali.cantine.dao.IUserDao;
import fr.sali.cantine.dto.in.UserDto;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.RoleEntity;
import fr.sali.cantine.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
@Service
public class SignUpService {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private IUserDao    userDao ;


    /**
     * @doc the method creat an  userEntity ,  crypt the password ,  and make  user as  default role, with  making also a default user image
     * @param  userdto DTO which  is  the  user information  sended  by  the client
     * @return userEntity storiged in  The DB
     * @throws 'toEntity' method  Exception
     */

    public UserEntity inscription(UserDto userdto,  String role)   throws  Exception{
        UserEntity   user =  userdto.toEntity();
        String password =  userdto.getPassword();

        /************** Cryptage du Mot de Passe ************************************/
        String  cryptedpassword = encoder.encode(password);
        user.setPassword(cryptedpassword);

        /************ Mettre le role <> Client par defaut à utilisateur</> ****************/

         RoleEntity userRole =  new RoleEntity() ;
         userRole.setLibelle(role);
         userRole.setDescription("utilisateur client peut ajouter au panier ces commandes ");
         List<RoleEntity> userRoles =  new LinkedList<>();
         userRoles.add(userRole);
         user.setRoles(userRoles);
         user.setStatus(1);
         user.setCreationDate(LocalDate.now());

         /******************* Mettre l'image par défaut ********************************/
         File image =  new File("src/main/resources/photoprofile.png");
         var fis = new FileInputStream(image);
         ImageEntity imageEntity =  new ImageEntity();
         imageEntity.setImage(fis.readAllBytes());
         fis.close();
         user.setImage(imageEntity);

         /* Test son  email   */
        return userDao.save(user);
    }


    public boolean existingUser (String email ) {
        if (email == null || email.trim().length() < 22  || !email.endsWith("@social.aston-ecole.com"))
            return  false ;
        var  user  =  userDao.findByEmail(email);
        if (user.isPresent()) {
            return  true  ;
        }

        return  false ;
    }


}
