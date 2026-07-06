package com.Dhiraj.Online.Food.ordering.Service.ServiceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Model.Category;
import com.Dhiraj.Online.Food.ordering.Model.Restaurant;
import com.Dhiraj.Online.Food.ordering.Repository.CategoryRepository;
import com.Dhiraj.Online.Food.ordering.Service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final RestaurantServiceImp restaurantService;
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String categoryName, Long userId) throws RestaurantException {
        Restaurant restaurant = restaurantService.getRestaurantsByUserId(userId);
        Category createdCategory = new Category();
        createdCategory.setName(categoryName);
        createdCategory.setRestaurant(restaurant);
        return categoryRepository.save(createdCategory);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException {
        return categoryRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Category findCategoryById(Long id) throws RestaurantException {
        Optional<Category> opt = categoryRepository.findById(id);
        if (opt.isEmpty()) {
            throw new RestaurantException("category not exist with id " + id);
        }
        return opt.get();
    }

}
