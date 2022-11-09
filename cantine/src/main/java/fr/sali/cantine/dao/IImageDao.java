package fr.sali.cantine.dao;

import fr.sali.cantine.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageDao extends JpaRepository<ImageEntity ,  Integer> {
}
