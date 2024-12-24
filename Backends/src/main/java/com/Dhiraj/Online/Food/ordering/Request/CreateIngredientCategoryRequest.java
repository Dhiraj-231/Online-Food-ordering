package com.Dhiraj.Online.Food.ordering.Request;

import lombok.Data;

@Data
public class CreateIngredientCategoryRequest {
    private Long restaurantId;
    private String name;
}
