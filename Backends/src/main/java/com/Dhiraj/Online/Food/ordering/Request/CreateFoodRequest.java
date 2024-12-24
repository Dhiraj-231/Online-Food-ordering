package com.Dhiraj.Online.Food.ordering.Request;

import java.util.List;

import com.Dhiraj.Online.Food.ordering.Model.Category;
import com.Dhiraj.Online.Food.ordering.Model.IngredientsItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long restaurantId;

    private boolean vegetarian;
    private boolean seasonal;

    private List<IngredientsItem> ingredients;
}
