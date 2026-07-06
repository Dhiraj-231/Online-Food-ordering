package com.Dhiraj.Online.Food.ordering.Service.ServiceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Model.IngredientCategory;
import com.Dhiraj.Online.Food.ordering.Model.IngredientsItem;
import com.Dhiraj.Online.Food.ordering.Model.Restaurant;
import com.Dhiraj.Online.Food.ordering.Repository.IngredientsCategoryRepository;
import com.Dhiraj.Online.Food.ordering.Repository.IngredientsItemRepository;
import com.Dhiraj.Online.Food.ordering.Service.IngredientsService;
import com.Dhiraj.Online.Food.ordering.Service.RestaurantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientsServiceImplementation implements IngredientsService {

    private final IngredientsCategoryRepository ingredientsCategoryRepository;
    private final IngredientsItemRepository ingredientsItemRepository;
    private final RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientsCategory(String name, Long restaurantId) throws RestaurantException {
        IngredientCategory isExist = ingredientsCategoryRepository
                .findByRestaurantIdAndNameIgnoreCase(restaurantId, name);

        if (isExist != null) {
            return isExist;
        }

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setRestaurant(restaurant);
        ingredientCategory.setName(name);

        IngredientCategory createdCategory = ingredientsCategoryRepository.save(ingredientCategory);

        return createdCategory;
    }

    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientName, Long ingredientCategoryId)
            throws Exception {

        IngredientCategory category = findIngredientsCategoryById(ingredientCategoryId);

        IngredientsItem isExist = ingredientsItemRepository.findByRestaurantIdAndNameIngoreCase(restaurantId,
                ingredientName, category.getName());
        if (isExist != null) {
            System.out.println("is exists-------- item");
            return isExist;
        }

        Restaurant restaurant = restaurantService.findRestaurantById(
                restaurantId);
        IngredientsItem item = new IngredientsItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

        IngredientsItem savedIngredients = ingredientsItemRepository.save(item);
        category.getIngredients().add(savedIngredients);

        return savedIngredients;
    }

    @Override
    public IngredientCategory findIngredientsCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientsCategoryRepository.findById(id);
        if (opt.isEmpty()) {
            throw new Exception("ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception {
        return ingredientsCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
        return ingredientsItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> item = ingredientsItemRepository.findById(id);
        if (item.isEmpty()) {
            throw new Exception("ingredient not found with id " + item);
        }
        IngredientsItem ingredient = item.get();
        ingredient.setInStoke(!ingredient.isInStoke());
        return ingredientsItemRepository.save(ingredient);
    }

}
