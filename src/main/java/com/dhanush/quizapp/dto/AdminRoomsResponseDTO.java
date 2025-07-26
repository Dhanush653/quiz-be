package com.dhanush.quizapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdminRoomsResponseDTO {

    private int noOfRooms;

    private int noOfActive;

    private int noOfInActive;

    private List<AdminScreenDTO> adminScreenDTOS;
}
