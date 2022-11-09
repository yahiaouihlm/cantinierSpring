package fr.sali.cantine.dao;

import fr.sali.cantine.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageDao extends JpaRepository<ImageEntity, Integer> {
}
