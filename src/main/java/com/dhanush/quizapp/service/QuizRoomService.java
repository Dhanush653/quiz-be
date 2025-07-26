package com.dhanush.quizapp.service;

import com.dhanush.quizapp.dto.QuestionDTO;
import com.dhanush.quizapp.dto.QuizRoomCreateDTO;
import com.dhanush.quizapp.entity.QuestionEntity;
import com.dhanush.quizapp.entity.QuizAccessEntity;
import com.dhanush.quizapp.entity.QuizRoomEntity;
import com.dhanush.quizapp.entity.UserEntity;
import com.dhanush.quizapp.repository.QuestionRepository;
import com.dhanush.quizapp.repository.QuizAccessRepository;
import com.dhanush.quizapp.repository.QuizRoomRepository;
import com.dhanush.quizapp.repository.UserRepository;
import com.dhanush.quizapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizRoomService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRoomRepository quizRoomRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizAccessRepository quizAccessRepository;

    public String createQuizRoom(QuizRoomCreateDTO dto, String token){
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Admin not found"));

        if(!user.isAdmin()){
            throw new RuntimeException("User is not Admin");
        }

        QuizRoomEntity quizRoom = new QuizRoomEntity();
        quizRoom.setTitle(dto.getTitle());
        quizRoom.setDescription(dto.getDescription());
        quizRoom.setNumberOfQuestions(dto.getQuestions().size());
        quizRoom.setDurationMinutes(dto.getDuration());
        quizRoom.setDeadlineTime(dto.getDeadLine());
        quizRoom.setCreatedBy(user);
        quizRoom = quizRoomRepository.save(quizRoom);

        int questionNumber = 1;
        for(QuestionDTO questionDTO : dto.getQuestions()){
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setQuizRoom(quizRoom);
            questionEntity.setQuestionNumber(questionNumber++);
            questionEntity.setQuestionText(questionDTO.getQuestion());
            questionEntity.setOptionA(questionDTO.getOptions().get(0));
            questionEntity.setOptionB(questionDTO.getOptions().get(1));
            questionEntity.setOptionC(questionDTO.getOptions().get(2));
            questionEntity.setOptionD(questionDTO.getOptions().get(3));

            String correct = switch (questionDTO.getCorrectOptionIndex()) {
                case 0 -> "A";
                case 1 -> "B";
                case 2 -> "C";
                case 3 -> "D";
                default -> throw new RuntimeException("Invalid Option");
            };
            questionEntity.setCorrectOption(correct);
            questionRepository.save(questionEntity);
        }

        for(String allowedEmail : dto.getParticipantEmails()){
            QuizAccessEntity quizAccessEntity = new QuizAccessEntity();
            quizAccessEntity.setQuizRoom(quizRoom);
            quizAccessEntity.setEmail(allowedEmail);
            quizAccessRepository.save(quizAccessEntity);
        }

        return "Room created successfully";
    }
}
