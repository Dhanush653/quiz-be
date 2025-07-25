package com.dhanush.quizapp.service;

import com.dhanush.quizapp.dto.AuthResponseDTO;
import com.dhanush.quizapp.dto.LoginDTO;
import com.dhanush.quizapp.dto.RegistrationDTO;
import com.dhanush.quizapp.entity.UserEntity;
import com.dhanush.quizapp.repository.UserRepository;
import com.dhanush.quizapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

        return new AuthResponseDTO(token, user.getName(), user.getEmail(), user.isAdmin());
    }
}
