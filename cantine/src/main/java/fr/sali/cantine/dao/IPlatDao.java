package fr.sali.cantine.dao;

import fr.sali.cantine.entity.PlatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlatDao extends JpaRepository<PlatEntity, Integer> {
}
