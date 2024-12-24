package com.Dhiraj.Online.Food.ordering.Service;

import java.util.List;

import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Model.Category;

public interface CategoryService {

    public Category createCategory(String categoryName, Long userId) throws RestaurantException;

    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException;

    public Category findCategoryById(Long id) throws RestaurantException;
}
