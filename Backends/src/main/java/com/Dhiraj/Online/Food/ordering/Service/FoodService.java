package com.Dhiraj.Online.Food.ordering.Service;

import java.util.List;

import com.Dhiraj.Online.Food.ordering.Exceptions.FoodException;
import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Model.Category;
import com.Dhiraj.Online.Food.ordering.Model.Food;
import com.Dhiraj.Online.Food.ordering.Model.Restaurant;
import com.Dhiraj.Online.Food.ordering.Request.CreateFoodRequest;

public interface FoodService {
        public Food createFood(CreateFoodRequest req, Category category,
                        Restaurant restaurant) throws FoodException, RestaurantException;

        void deleteFood(Long foodId) throws FoodException;

        public List<Food> getRestaurantsFood(Long restaurantId,
                        boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory)
                        throws FoodException;

        public List<Food> searchFood(String keyword);

        public Food findFoodById(Long foodId) throws FoodException;

        public Food updateAvailibilityStatus(Long foodId) throws FoodException;
}
