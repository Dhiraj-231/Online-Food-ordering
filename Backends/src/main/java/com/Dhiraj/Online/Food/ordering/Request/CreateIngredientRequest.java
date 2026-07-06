package com.Dhiraj.Online.Food.ordering.Request;

import lombok.Data;

@Data
public class CreateIngredientRequest {
    private Long restaurantId;
    private String name;
    private Long ingredientCategoryId;
}
