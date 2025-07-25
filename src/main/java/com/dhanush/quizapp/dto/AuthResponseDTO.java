package com.dhanush.quizapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;

    private String name;

    private String email;

    private boolean isAdmin;
}
