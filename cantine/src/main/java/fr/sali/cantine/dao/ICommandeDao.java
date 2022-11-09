package fr.sali.cantine.dao;

import fr.sali.cantine.entity.CommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommandeDao extends JpaRepository<CommandeEntity, Integer> {
}
