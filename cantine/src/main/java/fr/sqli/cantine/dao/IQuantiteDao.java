package fr.sqli.cantine.dao;

import fr.sqli.cantine.entity.QuantiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IQuantiteDao {

    @Repository
    public interface IUserDao extends JpaRepository<QuantiteEntity, Integer> {

    }

}
