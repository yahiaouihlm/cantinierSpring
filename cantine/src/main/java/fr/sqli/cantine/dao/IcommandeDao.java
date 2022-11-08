package fr.sqli.cantine.dao;

import fr.sqli.cantine.entity.CommandeEntity;
import fr.sqli.cantine.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IcommandeDao {
    @Repository
    public interface IUserDao extends JpaRepository<CommandeEntity, Integer> {

    }


}
