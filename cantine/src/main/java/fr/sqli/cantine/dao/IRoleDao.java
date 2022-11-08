package fr.sqli.cantine.dao;

import fr.sqli.cantine.entity.RoleEntity;
import fr.sqli.cantine.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface IRoleDao extends JpaRepository<RoleEntity, Integer> {



}
