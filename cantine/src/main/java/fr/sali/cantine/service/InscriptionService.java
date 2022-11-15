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
import java.util.LinkedList;
import java.util.List;


@Service
public class InscriptionService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private IUserDao    userDao ;

    /**
     * @doc  La méthode créé un UserEntity crypte le mot de passe ,  met le role par defaut <h3>Client</h3> et l'image par défaut
     * @param userdto DTO utilisateur qui encapsule les donnée envoyé par La vue
     * @return  l'utilisateur stocké dans  la Base de donné
     * @throws Exception de le méthode toEntity
     */
    public UserEntity inscription(UserDto userdto)   throws  Exception{
        UserEntity   user =  userdto.toEntity();
        String password =  userdto.getPassword();

        /************** Cryptage du Mot de Passe ************************************/
        String  cryptedpassword = encoder.encode(password);
        user.setPassword(cryptedpassword);

        /************ Mettre le role <> Client par defaut à utilisateur</> ****************/
        RoleEntity userRole =  new RoleEntity() ;
         userRole.setLibelle("client");
         userRole.setDescription("utilisateur client peut ajouter au panier ces commandes ");
        List<RoleEntity> userRoles =  new LinkedList<>();
        userRoles.add(userRole);
         user.setRoles(userRoles);


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


}
