package com.Dhiraj.Online.Food.ordering.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Dhiraj.Online.Food.ordering.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u Where u.status='PENDING'")
    public List<User> getPenddingRestaurantOwners();

    public User findByEmail(String email);
}
