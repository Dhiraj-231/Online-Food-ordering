package com.Dhiraj.Online.Food.ordering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dhiraj.Online.Food.ordering.Model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
