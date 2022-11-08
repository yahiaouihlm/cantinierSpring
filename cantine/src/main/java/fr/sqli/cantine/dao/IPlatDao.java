package fr.sqli.cantine.dao;

import fr.sqli.cantine.entity.PlatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




    @Repository
    public interface IPlatDao extends JpaRepository<PlatEntity, Integer> {

    }


