package fr.sali.cantine.dao;


import fr.sali.cantine.entity.ConfirmationToken;
import fr.sali.cantine.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IConfirmationToken extends JpaRepository <ConfirmationToken ,  String>  {
       Optional<ConfirmationToken> findByConfirmationToken( String confirmationToken);
       @Query("SELECT ct FROM ConfirmationToken ct WHERE ct.useruuid = ?1")
       Optional<ConfirmationToken> findByUuiduser(Integer uuiduser) ;

       @Query("SELECT ct FROM ConfirmationToken ct WHERE ct.user= ?1")
       Optional<ConfirmationToken> findByUser(UserEntity user);
}
