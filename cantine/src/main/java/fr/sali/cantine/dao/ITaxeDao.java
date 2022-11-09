package fr.sali.cantine.dao;

import fr.sali.cantine.entity.TaxeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaxeDao   extends JpaRepository <TaxeEntity ,Integer> {
}
