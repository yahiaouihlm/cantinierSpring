package fr.sali.cantine.dao;


import fr.sali.cantine.entity.QuantiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQantiteDao extends JpaRepository<QuantiteEntity , Integer> {
}
