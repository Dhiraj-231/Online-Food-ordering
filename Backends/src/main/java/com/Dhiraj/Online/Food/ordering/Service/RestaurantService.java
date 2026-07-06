package com.Dhiraj.Online.Food.ordering.Service;

import java.util.List;

import com.Dhiraj.Online.Food.ordering.Dto.RestaurantDto;
import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Model.Restaurant;
import com.Dhiraj.Online.Food.ordering.Model.User;
import com.Dhiraj.Online.Food.ordering.Request.CreateRestaurantRequest;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
    
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant)
            throws RestaurantException;

    public void deleteRestaurant(Long restaurantId) throws RestaurantException;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws RestaurantException;

    public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws RestaurantException;

    public Restaurant updateRestaurantStatus(Long id) throws RestaurantException;
}
