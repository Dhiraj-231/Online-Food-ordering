package com.Dhiraj.Online.Food.ordering.Request;

import com.Dhiraj.Online.Food.ordering.Model.Address;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private Long restaurantId;

    private Address deliveryAddress;
}
