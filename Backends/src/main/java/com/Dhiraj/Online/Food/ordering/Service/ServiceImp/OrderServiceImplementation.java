package com.Dhiraj.Online.Food.ordering.Service.ServiceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dhiraj.Online.Food.ordering.Exceptions.CartException;
import com.Dhiraj.Online.Food.ordering.Exceptions.OrderException;
import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Exceptions.UserException;
import com.Dhiraj.Online.Food.ordering.Model.Address;
import com.Dhiraj.Online.Food.ordering.Model.Cart;
import com.Dhiraj.Online.Food.ordering.Model.CartItem;
import com.Dhiraj.Online.Food.ordering.Model.Notification;
import com.Dhiraj.Online.Food.ordering.Model.Order;
import com.Dhiraj.Online.Food.ordering.Model.OrderItem;
import com.Dhiraj.Online.Food.ordering.Model.PaymentResponse;
import com.Dhiraj.Online.Food.ordering.Model.Restaurant;
import com.Dhiraj.Online.Food.ordering.Model.User;
import com.Dhiraj.Online.Food.ordering.Repository.AddressRepository;
import com.Dhiraj.Online.Food.ordering.Repository.OrderItemRepository;
import com.Dhiraj.Online.Food.ordering.Repository.OrderRepository;
import com.Dhiraj.Online.Food.ordering.Repository.RestaurantRepository;
import com.Dhiraj.Online.Food.ordering.Repository.UserRepository;
import com.Dhiraj.Online.Food.ordering.Request.CreateOrderRequest;
import com.Dhiraj.Online.Food.ordering.Service.CartService;
import com.Dhiraj.Online.Food.ordering.Service.NotificationService;
import com.Dhiraj.Online.Food.ordering.Service.OrderService;
import com.Dhiraj.Online.Food.ordering.Service.PaymentService;
import com.stripe.exception.StripeException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {
    private final AddressRepository addressRepository;
    private final CartService cartService;
    private final OrderItemRepository orderItemRepository;

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    private final PaymentService paymentService;
    private final NotificationService notificationService;

    @Override
    public void cancelOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        if (order == null) {
            throw new OrderException("Order not found with the id " + orderId);
        }

        orderRepository.deleteById(orderId);

    }

    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent())
            return order.get();

        throw new OrderException("Order not found with the id " + orderId);
    }

    @Override
    public PaymentResponse createOrder(CreateOrderRequest order, User user)
            throws UserException, RestaurantException, CartException, StripeException {
        Address shippAddress = order.getDeliveryAddress();

        Address savedAddress = addressRepository.save(shippAddress);

        if (!user.getAddresses().contains(savedAddress)) {
            user.getAddresses().add(savedAddress);
        }

        userRepository.save(user);

        Optional<Restaurant> restaurant = restaurantRepository.findById(order.getRestaurantId());
        if (restaurant.isEmpty()) {
            throw new RestaurantException("Restaurant not found with id " + order.getRestaurantId());
        }

        Order createdOrder = new Order();

        createdOrder.setCustomer(user);
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setRestaurant(restaurant.get());

        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getFood().getPrice() * cartItem.getQuantity());

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }

        Long totalPrice = cartService.calculateCartTotals(cart);

        createdOrder.setTotalAmount(totalPrice);
        createdOrder.setRestaurant(restaurant.get());

        createdOrder.setItems(orderItems);
        Order savedOrder = orderRepository.save(createdOrder);

        restaurant.get().getOrders().add(savedOrder);

        restaurantRepository.save(restaurant.get());

        PaymentResponse res = paymentService.generatePaymentLink(savedOrder);
        return res;
    }

    @Override
    public List<Order> getOrdersOfRestaurant(Long restaurantId, String orderStatus)
            throws OrderException, RestaurantException {
        List<Order> orders = orderRepository.findOrdersByRestaurantId(restaurantId);

        if (orderStatus != null) {
            orders = orders.stream()
                    .filter(order -> order.getOrderStatus().equals(orderStatus))
                    .collect(Collectors.toList());
        }

        return orders;
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws OrderException {
        List<Order> orders = orderRepository.findAllUserOrders(userId);
        return orders;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws OrderException {
        Order order = findOrderById(orderId);

        System.out.println("--------- " + orderStatus);

        if (orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")) {
            order.setOrderStatus(orderStatus);
            Notification notification = notificationService.sendOrderStatusNotification(order);
            return orderRepository.save(order);
        } else
            throw new OrderException("Please Select A Valid Order Status");
    }

}
