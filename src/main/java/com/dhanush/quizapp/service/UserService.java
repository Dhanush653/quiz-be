package com.dhanush.quizapp.service;

import com.dhanush.quizapp.dto.*;
import com.dhanush.quizapp.entity.QuizRoomEntity;
import com.dhanush.quizapp.entity.UserEntity;
import com.dhanush.quizapp.repository.QuizRoomRepository;
import com.dhanush.quizapp.repository.UserRepository;
import com.dhanush.quizapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRoomRepository quizRoomRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerNewUser(RegistrationDTO registrationDTO){
        if(userRepository.findByEmail(registrationDTO.getEmail()).isPresent()){
            return "User already exist";
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(registrationDTO.getName());
        userEntity.setEmail(registrationDTO.getEmail());
        userEntity.setAdmin(registrationDTO.isAdmin());
        userEntity.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        userRepository.save(userEntity);

        return "User registered";
    }

    public AuthResponseDTO loginUser(LoginDTO loginDTO){
        UserEntity user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user);

        return new AuthResponseDTO(token, user.getId(), user.getName(), user.getEmail(), user.isAdmin());
    }

    public AdminRoomsResponseDTO getAdminRooms(String token){
        int active = 0;
        int inactive = 0;
        LocalDateTime now = LocalDateTime.now();

        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if(!userEntity.isAdmin()){
            throw new RuntimeException("User is not admin");
        }

        List<QuizRoomEntity> quizRoom = quizRoomRepository.findByCreatedBy(userEntity);
        List<AdminScreenDTO> roomDTOs = new ArrayList<>();

        for(QuizRoomEntity entity : quizRoom){
            AdminScreenDTO dto = new AdminScreenDTO();
            dto.setId(entity.getId());
            dto.setDeadLine(entity.getDeadlineTime());
            dto.setTitle(entity.getTitle());
            dto.setDescription(entity.getDescription());
            dto.setDuration(entity.getDurationMinutes());
            dto.setNumberOfQuestions(entity.getNumberOfQuestions());
            roomDTOs.add(dto);

            if(entity.getDeadlineTime().isAfter(now)){
                active++;
            } else {
                inactive++;
            }
        }

        AdminRoomsResponseDTO adminRoomsResponseDTO = new AdminRoomsResponseDTO();
        adminRoomsResponseDTO.setAdminScreenDTOS(roomDTOs);
        adminRoomsResponseDTO.setNoOfRooms(roomDTOs.size());
        adminRoomsResponseDTO.setNoOfActive(active);
        adminRoomsResponseDTO.setNoOfInActive(inactive);

        return adminRoomsResponseDTO;
    }
}
