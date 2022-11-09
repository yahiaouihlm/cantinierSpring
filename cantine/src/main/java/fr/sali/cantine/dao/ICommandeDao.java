package fr.sali.cantine.dao;

import fr.sali.cantine.entity.CommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommandeDao extends JpaRepository<CommandeEntity , Integer> {
}
