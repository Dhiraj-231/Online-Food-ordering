package com.Dhiraj.Online.Food.ordering.Service.ServiceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Dhiraj.Online.Food.ordering.Exceptions.FoodException;
import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Model.Category;
import com.Dhiraj.Online.Food.ordering.Model.Food;
import com.Dhiraj.Online.Food.ordering.Model.Restaurant;
import com.Dhiraj.Online.Food.ordering.Repository.FoodRepository;
import com.Dhiraj.Online.Food.ordering.Request.CreateFoodRequest;
import com.Dhiraj.Online.Food.ordering.Service.FoodService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImp implements FoodService {

    private final FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant)
            throws FoodException, RestaurantException {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setCreationDate(new Date());
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice((long) req.getPrice());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());
        food.setIngredients(req.getIngredients());
        food.setRestaurant(restaurant);
        food = foodRepository.save(food);

        restaurant.getFoods().add(food);
        return food;
    }

    @Override
    public void deleteFood(Long foodId) throws FoodException {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.delete(food);
    }

    @Override
    public Food findFoodById(Long foodId) throws FoodException {
        Optional<Food> food = foodRepository.findById(foodId);
        if (food.isPresent()) {
            return food.get();
        }
        throw new FoodException("food with id" + foodId + "not found");
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal,
            String foodCategory) throws FoodException {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if (isVegetarian) {
            foods = filterByVegetarian(foods, isVegetarian);
        }
        if (isNonveg) {
            foods = filterByNonveg(foods, isNonveg);
        }

        if (isSeasonal) {
            foods = filterBySeasonal(foods, isSeasonal);
        }
        if (foodCategory != null && !foodCategory.equals("")) {
            foods = filterByFoodCategory(foods, foodCategory);
        }

        return foods;
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream()
                .filter(food -> food.isVegetarian() == isVegetarian)
                .collect(Collectors.toList());
    }

    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream()
                .filter(food -> food.isVegetarian() == false)
                .collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream()
                .filter(food -> food.isSeasonal() == isSeasonal)
                .collect(Collectors.toList());
    }

    private List<Food> filterByFoodCategory(List<Food> foods, String foodCategory) {

        return foods.stream()
                .filter(food -> {
                    if (food.getFoodCategory() != null) {
                        return food.getFoodCategory().getName().equals(foodCategory);
                    }
                    return false; // Return true if food category is null
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        List<Food> items = new ArrayList<>();

        if (keyword != "") {

            items = foodRepository.searchByNameOrCategory(keyword);
        }

        return items;
    }

    @Override
    public Food updateAvailibilityStatus(Long foodId) throws FoodException {
        Food food = findFoodById(foodId);

        food.setAvailable(!food.isAvailable());
        foodRepository.save(food);
        return food;
    }

}
