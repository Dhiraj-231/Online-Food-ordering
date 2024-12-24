package com.Dhiraj.Online.Food.ordering.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dhiraj.Online.Food.ordering.Model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    public List<Notification> findByCustomerId(Long userId);

    public List<Notification> findByRestaurantId(Long restaurantId);
}
