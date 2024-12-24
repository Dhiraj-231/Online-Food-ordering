package com.Dhiraj.Online.Food.ordering.Service.ServiceImp;

import java.util.Date;
import java.util.List;

import com.Dhiraj.Online.Food.ordering.Model.Notification;
import com.Dhiraj.Online.Food.ordering.Model.Order;
import com.Dhiraj.Online.Food.ordering.Model.Restaurant;
import com.Dhiraj.Online.Food.ordering.Model.User;
import com.Dhiraj.Online.Food.ordering.Repository.NotificationRepository;
import com.Dhiraj.Online.Food.ordering.Service.NotificationService;

public class NotificationServiceImplementation implements NotificationService {

    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> findUsersNotification(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Notification sendOrderStatusNotification(Order order) {
        Notification notification = new Notification();
        notification.setMessage("your order is " + order.getOrderStatus() + " order id is - " + order.getId());
        notification.setCustomer(order.getCustomer());
        notification.setSentAt(new Date());

        return notificationRepository.save(notification);
    }

    @Override
    public void sendPromotionalNotification(User user, String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendRestaurantNotification(Restaurant restaurant, String message) {
        // TODO Auto-generated method stub

    }

}
