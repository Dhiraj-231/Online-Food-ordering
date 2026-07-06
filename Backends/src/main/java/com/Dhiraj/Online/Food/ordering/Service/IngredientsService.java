package com.Dhiraj.Online.Food.ordering.Service;

import java.util.List;

import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Model.IngredientCategory;
import com.Dhiraj.Online.Food.ordering.Model.IngredientsItem;

public interface IngredientsService {
        public IngredientCategory createIngredientsCategory(
                        String name, Long restaurantId) throws RestaurantException;

        public IngredientCategory findIngredientsCategoryById(Long id) throws Exception;

        public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;

        public List<IngredientsItem> findRestaurantsIngredients(
                        Long restaurantId);

        public IngredientsItem createIngredientsItem(Long restaurantId,
                        String ingredientName, Long ingredientCategoryId) throws Exception;

        public IngredientsItem updateStock(Long id) throws Exception;
}
