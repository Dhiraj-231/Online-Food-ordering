package com.Dhiraj.Online.Food.ordering.Service;

import com.Dhiraj.Online.Food.ordering.Exceptions.CartException;
import com.Dhiraj.Online.Food.ordering.Exceptions.CartItemException;
import com.Dhiraj.Online.Food.ordering.Exceptions.FoodException;
import com.Dhiraj.Online.Food.ordering.Exceptions.UserException;
import com.Dhiraj.Online.Food.ordering.Model.Cart;
import com.Dhiraj.Online.Food.ordering.Model.CartItem;
import com.Dhiraj.Online.Food.ordering.Request.AddCartItemRequest;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, String jwt)
            throws UserException, FoodException, CartException, CartItemException;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws CartItemException;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

    public Long calculateCartTotals(Cart cart) throws UserException;

    public Cart findCartById(Long id) throws CartException;

    public Cart findCartByUserId(Long userId) throws CartException, UserException;

    public Cart clearCart(Long userId) throws CartException, UserException;
}
