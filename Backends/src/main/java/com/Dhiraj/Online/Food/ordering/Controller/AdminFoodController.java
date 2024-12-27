package com.Dhiraj.Online.Food.ordering.Controller;

import java.util.List;

import com.Dhiraj.Online.Food.ordering.Service.ServiceImp.FoodServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Dhiraj.Online.Food.ordering.Exceptions.FoodException;
import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Exceptions.UserException;
import com.Dhiraj.Online.Food.ordering.Model.Food;
import com.Dhiraj.Online.Food.ordering.Model.Restaurant;
import com.Dhiraj.Online.Food.ordering.Model.User;
import com.Dhiraj.Online.Food.ordering.Request.CreateFoodRequest;
import com.Dhiraj.Online.Food.ordering.Service.FoodService;
import com.Dhiraj.Online.Food.ordering.Service.RestaurantService;
import com.Dhiraj.Online.Food.ordering.Service.ServiceImp.UserServiceImp;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/food")
@RequiredArgsConstructor
public class AdminFoodController { 
    private final FoodServiceImp foodService;
    private final UserServiceImp userService;
    private final RestaurantService restaurantService;

    @PostMapping()
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
            @RequestHeader("Authorization") String jwt) throws FoodException, UserException, RestaurantException {
        User user = userService.findUserProfileByJwt(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food menuItem = foodService.createFood(req, req.getCategory(), restaurant);
        return ResponseEntity.ok(menuItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
            throws UserException, FoodException {
        User user = userService.findUserProfileByJwt(jwt);

        foodService.deleteFood(id);
        return ResponseEntity.ok("Menu item deleted");

    }

    @GetMapping("/search")
    public ResponseEntity<List<Food>> getMenuItemByName(@RequestParam String name) {
        List<Food> menuItem = foodService.searchFood(name);
        return ResponseEntity.ok(menuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateAvilibilityStatus(
            @PathVariable Long id) throws FoodException {
        Food menuItems = foodService.updateAvailibilityStatus(id);
        return ResponseEntity.ok(menuItems);
    }
}
