package fr.sali.cantine.dao;

import fr.sali.cantine.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IImageDao extends JpaRepository<ImageEntity, Integer> {
}
