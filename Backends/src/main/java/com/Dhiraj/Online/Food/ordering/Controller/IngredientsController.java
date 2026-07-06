package com.Dhiraj.Online.Food.ordering.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dhiraj.Online.Food.ordering.Model.IngredientCategory;
import com.Dhiraj.Online.Food.ordering.Model.IngredientsItem;
import com.Dhiraj.Online.Food.ordering.Request.CreateIngredientCategoryRequest;
import com.Dhiraj.Online.Food.ordering.Request.CreateIngredientRequest;
import com.Dhiraj.Online.Food.ordering.Service.IngredientsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/ingredients")
@RequiredArgsConstructor
public class IngredientsController {
    private final IngredientsService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody CreateIngredientCategoryRequest req) throws Exception {
        IngredientCategory items = ingredientService.createIngredientsCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItem> createIngredient(
            @RequestBody CreateIngredientRequest req) throws Exception {

        IngredientsItem item = ingredientService.createIngredientsItem(req.getRestaurantId(), req.getName(),
                req.getIngredientCategoryId());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItem> updateStocke(@PathVariable Long id) throws Exception {
        IngredientsItem item = ingredientService.updateStock(id);
        return new ResponseEntity<IngredientsItem>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> restaurantsIngredient(
            @PathVariable Long id) throws Exception {
        List<IngredientsItem> items = ingredientService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> restaurantsIngredientCategory(
            @PathVariable Long id) throws Exception {
        List<IngredientCategory> items = ingredientService.findIngredientsCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
