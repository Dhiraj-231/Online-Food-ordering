package com.Dhiraj.Online.Food.ordering.Service.ServiceImp;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Dhiraj.Online.Food.ordering.Model.Notification;
import com.Dhiraj.Online.Food.ordering.Model.Order;
import com.Dhiraj.Online.Food.ordering.Model.Restaurant;
import com.Dhiraj.Online.Food.ordering.Model.User;
import com.Dhiraj.Online.Food.ordering.Repository.NotificationRepository;
import com.Dhiraj.Online.Food.ordering.Service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImplementation implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public List<Notification> findUsersNotification(Long userId) {
        return notificationRepository.findByCustomerId(userId);
    }

    @Override
    public void deleteNotification(Long userId, Long notificationId) {
        Notification notification = notificationRepository.findByIdAndCustomerId(notificationId, userId);
        if (notification != null) {
            notificationRepository.delete(notification);
        }
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
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setRestaurant(restaurant);
        notification.setSentAt(new Date());
        notificationRepository.save(notification);

    }

}
