package fr.sali.cantine.dao;

import fr.sali.cantine.entity.PlatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlatDao extends JpaRepository<PlatEntity , Integer> {
}
