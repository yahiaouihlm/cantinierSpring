package fr.sqli.cantine.dao;

import fr.sqli.cantine.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IMenuDao {
    @Repository
    public interface IUserDao extends JpaRepository<MenuEntity, Integer> {

    }

}
