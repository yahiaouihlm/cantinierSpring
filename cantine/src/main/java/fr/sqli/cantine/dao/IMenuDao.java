package fr.sqli.cantine.dao;

import fr.sqli.cantine.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface IMenuDao extends JpaRepository<MenuEntity, Integer> {

    }


