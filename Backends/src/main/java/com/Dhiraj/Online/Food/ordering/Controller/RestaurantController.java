package com.Dhiraj.Online.Food.ordering.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Dhiraj.Online.Food.ordering.Dto.RestaurantDto;
import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Exceptions.UserException;
import com.Dhiraj.Online.Food.ordering.Model.Restaurant;
import com.Dhiraj.Online.Food.ordering.Model.User;
import com.Dhiraj.Online.Food.ordering.Service.RestaurantService;
import com.Dhiraj.Online.Food.ordering.Service.ServiceImp.UserServiceImp;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final UserServiceImp userService;

    /**
     * This method is used to search a restaurant by name.
     * 
     * @param keyword the name of the restaurant
     * @return a list of restaurant matching the name
     */
    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> findRestaurantByName(
            @RequestParam String keyword) {
        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);

        return ResponseEntity.ok(restaurant);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {

        List<Restaurant> restaurants = restaurantService.getAllRestaurant();

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @PathVariable Long id) throws RestaurantException {

        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return ResponseEntity.ok(restaurant);

    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorite(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws RestaurantException, UserException {

        User user = userService.findUserProfileByJwt(jwt);
        RestaurantDto restaurant = restaurantService.addToFavorites(id, user);
        return ResponseEntity.ok(restaurant);
    }
}
