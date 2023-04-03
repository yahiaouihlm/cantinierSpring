package fr.sali.cantine.service;


import fr.sali.cantine.dao.IConfirmationToken;
import fr.sali.cantine.dao.IUserDao;
import fr.sali.cantine.dto.in.UserValidationCode;
import fr.sali.cantine.entity.ConfirmationToken;
import fr.sali.cantine.entity.UserEntity;
import fr.sali.cantine.service.exception.ExpiredCode;
import fr.sali.cantine.service.exception.InvalidInformation;
import fr.sali.cantine.service.exception.InvalidUserCode;
import fr.sali.cantine.service.exception.UserNotFoundException;
import fr.sali.cantine.service.mailer.EmailSenderService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForgetPasswordService {

      @Autowired
      private BCryptPasswordEncoder encoder;
        @Autowired
        IUserDao  iUserDao;

        @Autowired
    IConfirmationToken iConfirmationToken;
    @Autowired
    private EmailSenderService emailSenderService ;
        public  void  sendMailToUpdatePassword  ( String email )  throws UserNotFoundException {
           if  ( email == null || email.isEmpty())
               throw   new UserNotFoundException(" Invalid  Email ");
            var  userEntity =  iUserDao.findByEmail(email);

            if  (!userEntity.isPresent())
                  throw   new UserNotFoundException(" User Not  Found  with  Email = " +   email );
            var  user =  userEntity.get();
            ConfirmationToken token  =  new ConfirmationToken(user);
           // this.iConfirmationToken.save(token);
            SimpleMailMessage mailMessage =  new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            System.out.println("je vais  envoyer le mail  a  " +user.getEmail());
            mailMessage.setSubject("Confirm Your Acount ");
            mailMessage.setFrom("Cantiniere@aston-ecole.com");
            mailMessage.setText(" Bonjour  \n " + user.getUsername() + "  "+ user.getUserfname() +"\n" + " Votre Code De confirmation pour Rénitialiser Votre mot de Passe est  : "+token.getUseruuid() + "\n" + "Le code valable uniqument 10 min");
            this.emailSenderService.sendEmail (mailMessage);
        }



        public UserEntity checkCodeValidator  (String  email , Integer  code ) throws InvalidUserCode, UserNotFoundException, ExpiredCode {
            System.out.println(code);

            if (email == null || code == null || email.isEmpty() || code < 1000000 )
                 throw  new  InvalidUserCode("Invalid User Code ");


            Optional<UserEntity> byEmail = this.iUserDao.findByEmail(email);
              if  (!byEmail.isPresent())
                  throw new UserNotFoundException("User Not Found ");



            Optional<ConfirmationToken> byUuiduser = this.iConfirmationToken.findByUuiduser(code);
            if (!byUuiduser.isPresent())
                     throw   new InvalidUserCode("Invalid Code ");



            if  (!byUuiduser.get().getUser().getEmail().equals(byEmail.get().getEmail()))
                         throw  new  InvalidUserCode ("USER NOT  FOUND  ");

            var tokenDB =  byUuiduser.get();
            var  expiredTime  = System.currentTimeMillis() - tokenDB.getCreatedDate().getTime();
            long fiveMinutesInMillis = 30 * 60 * 1000; // 5 minutes en millisecondes
            if (expiredTime > fiveMinutesInMillis){
                this.iConfirmationToken.delete(tokenDB);
                throw  new ExpiredCode("Code Has Been Expired ");
            }

            return  byEmail.get() ;

        }


        /* TODO  continuer a travailler sur le  changepassword  */

        public  void  changePassword (UserValidationCode userinfo ) throws UserNotFoundException, InvalidUserCode, ExpiredCode, InvalidInformation {
            var  newpassword =  userinfo.getPassword();
            if  (newpassword == null || newpassword.isEmpty() || newpassword.length() < 6 ) {
                throw  new InvalidInformation(" INVALID  PASSWORLD ");
            }
           var  user =   checkCodeValidator(userinfo.getEmail(), userinfo.getCode());


           newpassword =  encoder.encode(newpassword);

           user.setPassword(newpassword);
           this.iUserDao.save(user);

           /*TODO :  Effacer Le copnfirmation  Token  dans  La base de donnes  meme pour  valider le  compte */

        }




}
