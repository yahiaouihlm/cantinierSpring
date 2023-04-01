package fr.sali.cantine.service;


import fr.sali.cantine.dao.IUserDao;
import fr.sali.cantine.entity.ConfirmationToken;
import fr.sali.cantine.entity.UserEntity;
import fr.sali.cantine.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class ForgetPasswordService {

        @Autowired
        IUserDao  iUserDao;
        public  void  sendMailToUpdatePassword  ( String email )  throws UserNotFoundException {
           if  ( email == null || email.isEmpty())
               throw   new UserNotFoundException(" Invalid  Email ");
            var  userEntity =  iUserDao.findByEmail(email);

            if  (!userEntity.isPresent())
                  throw   new UserNotFoundException(" User Not  Found  with  Email = " +   email );
            var  user =  userEntity.get();
            ConfirmationToken token  =  new ConfirmationToken(user);

            SimpleMailMessage mailMessage =  new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            System.out.println("je vais  envoyer le mail  a  " +user.getEmail());
            mailMessage.setSubject("Confirm Your Acount ");
            mailMessage.setFrom("Cantiniere@aston-ecole.com");
            mailMessage.setText(" Bonjour  \n " + user.getUsername() + "  "+ user.getUserfname() +"\n" + " Votre Code De confirmation pour Valider  Votre compte est : "+token.getUseruuid() + "\n" + "Le code valable uniqument 10 min");


        }




}
