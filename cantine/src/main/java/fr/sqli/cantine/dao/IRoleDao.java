package fr.sqli.cantine.dao;

import fr.sqli.cantine.entity.RoleEntity;
import fr.sqli.cantine.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IRoleDao {
    @Repository
    public interface IUserDao extends JpaRepository<RoleEntity, Integer> {

    }

}
