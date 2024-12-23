package com.Dhiraj.Online.Food.ordering.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dhiraj.Online.Food.ordering.Exceptions.UserException;
import com.Dhiraj.Online.Food.ordering.Model.User;
import com.Dhiraj.Online.Food.ordering.Service.userService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final userService userService;

    @GetMapping("/profile")
    private ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
}
