package com.dhanush.quizapp.dto;

import lombok.Data;

@Data
public class RegistrationDTO {

    private String name;

    private String email;

    private String password;

    private boolean isAdmin;
}
