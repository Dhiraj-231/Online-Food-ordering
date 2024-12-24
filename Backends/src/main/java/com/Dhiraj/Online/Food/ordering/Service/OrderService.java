package com.Dhiraj.Online.Food.ordering.Service;

import java.util.List;

import com.Dhiraj.Online.Food.ordering.Exceptions.CartException;
import com.Dhiraj.Online.Food.ordering.Exceptions.OrderException;
import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Exceptions.UserException;
import com.Dhiraj.Online.Food.ordering.Model.Order;
import com.Dhiraj.Online.Food.ordering.Model.PaymentResponse;
import com.Dhiraj.Online.Food.ordering.Model.User;
import com.Dhiraj.Online.Food.ordering.Request.CreateOrderRequest;
import com.stripe.exception.StripeException;

public interface OrderService {
        public PaymentResponse createOrder(CreateOrderRequest order, User user)
                        throws UserException, RestaurantException, CartException, StripeException;

        public Order updateOrder(Long orderId, String orderStatus) throws OrderException;

        public void cancelOrder(Long orderId) throws OrderException;

        public List<Order> getUserOrders(Long userId) throws OrderException;

        public List<Order> getOrdersOfRestaurant(Long restaurantId, String orderStatus)
                        throws OrderException, RestaurantException;
}
