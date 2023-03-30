package fr.sali.cantine.service;

import fr.sali.cantine.dao.IConfirmationToken;
import fr.sali.cantine.dao.IUserDao;
import fr.sali.cantine.dto.in.UserDto;
import fr.sali.cantine.entity.ConfirmationToken;
import fr.sali.cantine.entity.ImageEntity;
import fr.sali.cantine.entity.RoleEntity;
import fr.sali.cantine.entity.UserEntity;
import fr.sali.cantine.service.images.ImageService;
import fr.sali.cantine.service.mailer.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Service
public class SignUpService {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private IUserDao    userDao ;

    @Autowired
    private ImageService imageService;

    @Autowired
    private IConfirmationToken iConfirmationToken;

    @Autowired
    private EmailSenderService emailSenderService ;
    /**
     * @doc the method creat an  userEntity ,  crypt the password ,  and make  user as  default role, with  making also a default user image
     * @return userEntity storiged in  The DB
     * @throws 'toEntity' method  Exception
     */


    public  void checkTokenLink(String token ) throws  Exception {
        var  tokenOpt =  this.iConfirmationToken.findByConfirmationToken(token);
        if  (!tokenOpt.isPresent()){
            throw   new IllegalArgumentException(" Token Not Found ");
        }
        var tokenDB =  tokenOpt.get();
        // Vérérifier la date de expiration  de 10 min
        var  expiredTime  = System.currentTimeMillis() - tokenDB.getCreatedDate().getTime();
        long fiveMinutesInMillis = 5 * 60 * 1000; // 5 minutes en millisecondes
        if (expiredTime > fiveMinutesInMillis){
            this.iConfirmationToken.delete(tokenDB);
            throw  new RuntimeException("Token Has Been Expired ");
        }
        var  userOpt =  this.userDao.findByEmail(tokenDB.getUser().getEmail());
        if (!userOpt.isPresent()){
            throw   new IllegalArgumentException(" Token Not Found ");
        }
        var  user =  userOpt.get();
        user.setStatus(1);
        this.userDao.save(user);

    }
    public UserEntity inscription(UserDto userdto,  String role)   throws  Exception{
        UserEntity   user =  userdto.toEntity();

        if (this.userDao.findByEmail(user.getEmail()).isPresent()) {
            throw   new IllegalArgumentException(" Email  Alrezdy  exist ");
        }
        String password =  userdto.getPassword();
        /************** Cryptage du Mot de Passe ************************************/
        String  cryptedpassword = encoder.encode(password);
        user.setPassword(cryptedpassword);
        /************ Mettre le role <> Client par defaut à utilisateur</> ****************/
         RoleEntity userRole =  new RoleEntity() ;
         userRole.setLibelle(role);
         if (role.equals("admin"))
            userRole.setDescription("Admin : administrateur du  site  ");
         else {
             userRole.setDescription("u un  étudiant de école  Aston  ");
         }
         List<RoleEntity> userRoles =  new LinkedList<>();
         userRoles.add(userRole);
         user.setRoles(userRoles);
         user.setStatus(0);
         user.setCreationDate(LocalDate.now());
         /******************* Mettre l'image par défaut ********************************/

         ImageEntity imageEntity =  new ImageEntity();

         if  (userdto.getImage() !=null ){
             MultipartFile image =  userdto.getImage();
             var imagename  =  this.imageService.uploadImage(image , "images/users");
             imageEntity.setNameimage(imagename);
         }
         else {
             imageEntity.setNameimage("defaultUserProfileImage.png");
         }

         user.setImage(imageEntity);
        return  userDao.save(user) ;
    }


    public void  mailSender ( String email) throws  Exception {
        var useropt = this.userDao.findByEmail(email );
        if (!useropt.isPresent()) {
            throw   new IllegalArgumentException(" Email  Already  exist ");
        }
       var  userEntity  =  useropt.get();
       if (userEntity.getStatus() == 1){
           throw   new IllegalArgumentException(" Email  Alredy  validated  ");
       }
        ConfirmationToken confirmationToken  =  new ConfirmationToken(userEntity);
        this.iConfirmationToken.save(confirmationToken);

        SimpleMailMessage mailMessage =  new SimpleMailMessage();
        mailMessage.setTo(userEntity.getEmail());
        System.out.println("je vais  envoyer le mail  a  " + userEntity.getEmail());
        mailMessage.setSubject("Confirm Your Acount ");
        mailMessage.setFrom("yahiaouiali4019@gmail.com");
        mailMessage.setText("Hello \n " + userEntity.getUsername() + " "+ userEntity.getUserfname() +"\n" + "To Confirm  your  Account Please click  here :  http://localhost:8080/cantine/user/confirm-acount?token="+ confirmationToken.getConfirmationToken());
        this.emailSenderService.sendEmail(mailMessage);
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
