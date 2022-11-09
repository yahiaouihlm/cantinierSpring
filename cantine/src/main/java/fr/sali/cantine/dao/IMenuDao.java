package fr.sali.cantine.dao;

import fr.sali.cantine.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMenuDao extends JpaRepository<MenuEntity, Integer> {
}
