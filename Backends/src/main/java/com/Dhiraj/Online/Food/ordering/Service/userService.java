package com.Dhiraj.Online.Food.ordering.Service;

import java.util.List;

import com.Dhiraj.Online.Food.ordering.Exceptions.UserException;
import com.Dhiraj.Online.Food.ordering.Model.User;

public interface userService {
    public User findUserProfileByJwt(String jwt) throws UserException;

    public User findUserByEmail(String email) throws UserException;

    public List<User> findAllUsers();

    public List<User> getPenddingRestaurantOwner();

    void updatePassword(User user, String newPassword);

    void sendPasswordResetEmail(User user);
}
