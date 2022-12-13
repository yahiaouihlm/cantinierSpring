package fr.sali.cantine.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.sali.cantine.entity.OrderEntity;

@Repository
public interface IOrderDao extends JpaRepository<OrderEntity, Integer> {
}
