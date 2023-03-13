package fr.sali.cantine.dao;

import fr.sali.cantine.entity.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface IMealDao extends JpaRepository<MealEntity, Integer> {
}