package com.Dhiraj.Online.Food.ordering.Response;

import com.Dhiraj.Online.Food.ordering.Domin.USER_ROLE;

import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private String jwt;
    private USER_ROLE role;
}
