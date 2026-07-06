package com.Dhiraj.Online.Food.ordering.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Dhiraj.Online.Food.ordering.Model.IngredientCategory;

public interface IngredientsCategoryRepository extends JpaRepository<IngredientCategory, Long> {
    public List<IngredientCategory> findByRestaurantId(Long id);

    @Query("SELECT e FROM IngredientCategory e "
            + "WHERE e.restaurant.id = :restaurantId "
            + "AND lower(e.name) = lower(:name)")
    IngredientCategory findByRestaurantIdAndNameIgnoreCase(
            @Param("restaurantId") Long restaurantId, @Param("name") String name);

}
