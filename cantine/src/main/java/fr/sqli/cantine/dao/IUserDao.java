package fr.sqli.cantine.dao;


import fr.sqli.cantine.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<UserEntity, Integer> {

}
