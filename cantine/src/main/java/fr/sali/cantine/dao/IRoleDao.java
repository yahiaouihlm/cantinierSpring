package fr.sali.cantine.dao;


import fr.sali.cantine.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDao  extends JpaRepository  <RoleEntity, Integer> {
}
