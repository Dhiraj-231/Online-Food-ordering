package com.Dhiraj.Online.Food.ordering.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dhiraj.Online.Food.ordering.Exceptions.UserException;
import com.Dhiraj.Online.Food.ordering.Model.Notification;
import com.Dhiraj.Online.Food.ordering.Model.User;
import com.Dhiraj.Online.Food.ordering.Service.NotificationService;
import com.Dhiraj.Online.Food.ordering.Service.ServiceImp.UserServiceImp;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationSerivce;
    private final UserServiceImp userService;

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> findUsersNotification(
            @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);

        List<Notification> notifications = notificationSerivce.findUsersNotification(user.getId());
        return new ResponseEntity<List<Notification>>(notifications, HttpStatus.ACCEPTED);
    }
}
