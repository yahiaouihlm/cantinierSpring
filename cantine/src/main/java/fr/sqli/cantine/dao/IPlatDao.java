package fr.sqli.cantine.dao;

import fr.sqli.cantine.entity.PlatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IPlatDao {


    @Repository
    public interface IUserDao extends JpaRepository<PlatEntity, Integer> {

    }

}
