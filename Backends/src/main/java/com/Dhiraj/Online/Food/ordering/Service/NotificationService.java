package com.Dhiraj.Online.Food.ordering.Service;

import java.util.List;

import com.Dhiraj.Online.Food.ordering.Model.Notification;
import com.Dhiraj.Online.Food.ordering.Model.Order;
import com.Dhiraj.Online.Food.ordering.Model.Restaurant;
import com.Dhiraj.Online.Food.ordering.Model.User;

public interface NotificationService {
    public Notification sendOrderStatusNotification(Order order);

    public void sendRestaurantNotification(Restaurant restaurant, String message);

    public void sendPromotionalNotification(User user, String message);

    public List<Notification> findUsersNotification(Long userId);
}
