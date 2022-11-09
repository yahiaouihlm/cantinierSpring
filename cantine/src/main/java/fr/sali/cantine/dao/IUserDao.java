package fr.sali.cantine.dao;


import fr.sali.cantine.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserDao  extends JpaRepository<UserEntity, Integer> {

    public  Optional <UserEntity>  findByEmail (String email);

}
