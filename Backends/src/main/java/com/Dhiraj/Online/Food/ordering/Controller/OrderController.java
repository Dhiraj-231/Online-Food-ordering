package com.Dhiraj.Online.Food.ordering.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dhiraj.Online.Food.ordering.Exceptions.CartException;
import com.Dhiraj.Online.Food.ordering.Exceptions.OrderException;
import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Exceptions.UserException;
import com.Dhiraj.Online.Food.ordering.Model.Order;
import com.Dhiraj.Online.Food.ordering.Model.PaymentResponse;
import com.Dhiraj.Online.Food.ordering.Model.User;
import com.Dhiraj.Online.Food.ordering.Request.CreateOrderRequest;
import com.Dhiraj.Online.Food.ordering.Service.OrderService;
import com.Dhiraj.Online.Food.ordering.Service.ServiceImp.UserServiceImp;
import com.stripe.exception.StripeException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserServiceImp userService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody CreateOrderRequest order,
            @RequestHeader("Authorization") String jwt)
            throws UserException, RestaurantException,
            CartException,
            StripeException,
            OrderException {
        User user = userService.findUserProfileByJwt(jwt);
        System.out.println("req user " + user.getEmail());
        if (order != null) {
            PaymentResponse res = orderService.createOrder(order, user);
            return ResponseEntity.ok(res);

        } else
            throw new OrderException("Please provide valid request body");

    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getAllUserOrders(@RequestHeader("Authorization") String jwt)
            throws OrderException, UserException {

        User user = userService.findUserProfileByJwt(jwt);

        if (user.getId() != null) {
            List<Order> userOrders = orderService.getUserOrders(user.getId());
            return ResponseEntity.ok(userOrders);
        } else {
            return new ResponseEntity<List<Order>>(HttpStatus.BAD_REQUEST);
        }
    }
}
