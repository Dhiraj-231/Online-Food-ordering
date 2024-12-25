package com.Dhiraj.Online.Food.ordering.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Dhiraj.Online.Food.ordering.Exceptions.OrderException;
import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Model.Order;
import com.Dhiraj.Online.Food.ordering.Service.OrderService;
import com.Dhiraj.Online.Food.ordering.Service.ServiceImp.UserServiceImp;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminOrderController {

    private final OrderService orderService;
    private final UserServiceImp userService;

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) throws OrderException {
        if (orderId != null) {
            orderService.cancelOrder(orderId);
            return ResponseEntity.ok("Order deleted with id)" + orderId);
        } else
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/order/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getAllRestaurantOrders(
            @PathVariable Long restaurantId,
            @RequestParam(required = false) String order_status) throws OrderException, RestaurantException {

        List<Order> orders = orderService.getOrdersOfRestaurant(restaurantId, order_status);

        // System.out.println("ORDER STATUS----- "+orderStatus);
        return ResponseEntity.ok(orders);

    }

    @PutMapping("/orders/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrders(@PathVariable Long orderId, @PathVariable String orderStatus)
            throws OrderException, RestaurantException {

        Order orders = orderService.updateOrder(orderId, orderStatus);
        return ResponseEntity.ok(orders);

    }
}
