package fr.sali.cantine.dao;

import fr.sali.cantine.entity.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMealDao extends JpaRepository<MealEntity, Integer> {
}
