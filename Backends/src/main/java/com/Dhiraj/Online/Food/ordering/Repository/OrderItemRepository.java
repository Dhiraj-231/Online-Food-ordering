package com.Dhiraj.Online.Food.ordering.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dhiraj.Online.Food.ordering.Model.Order;
import com.Dhiraj.Online.Food.ordering.Model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);

}
