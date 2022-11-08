package fr.sqli.cantine.dao;

import fr.sqli.cantine.entity.QuantiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



    @Repository
    public interface IQuantiteDao extends JpaRepository<QuantiteEntity, Integer> {

    }


