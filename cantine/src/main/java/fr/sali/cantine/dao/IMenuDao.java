package fr.sali.cantine.dao;

import fr.sali.cantine.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuDao extends JpaRepository<MenuEntity , Integer> {
}
