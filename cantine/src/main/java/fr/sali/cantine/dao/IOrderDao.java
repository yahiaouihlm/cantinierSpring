package fr.sali.cantine.dao;

import fr.sali.cantine.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDao extends JpaRepository<OrderEntity, Integer> {
}
