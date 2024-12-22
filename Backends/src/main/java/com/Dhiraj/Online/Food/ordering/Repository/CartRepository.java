package com.Dhiraj.Online.Food.ordering.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dhiraj.Online.Food.ordering.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
