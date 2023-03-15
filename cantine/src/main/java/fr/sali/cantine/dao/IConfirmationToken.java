package fr.sali.cantine.dao;


import fr.sali.cantine.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IConfirmationToken extends JpaRepository <ConfirmationToken ,  String>  {
       Optional<ConfirmationToken> findByConfirmationToken( String confirmationToken);
}
