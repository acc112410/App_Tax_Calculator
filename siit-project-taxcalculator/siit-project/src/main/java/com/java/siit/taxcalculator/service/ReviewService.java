package com.java.siit.taxcalculator.service;

import com.java.siit.taxcalculator.domain.entity.LoginEntity;
import com.java.siit.taxcalculator.domain.entity.ReviewEntity;
import com.java.siit.taxcalculator.domain.model.ReviewDTO;
import com.java.siit.taxcalculator.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final ReviewRepository reviewRepository;


    public List<ReviewEntity> getAllReviews(){
        return repository.findAll();
    }

    public ReviewEntity createReview(ReviewDTO dto) {
        return reviewRepository.save(ReviewEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .loginEntity(LoginEntity.builder()
                        .id(dto.getLoginDTO().getId())
                        .email(dto.getLoginDTO().getEmail())
                        .firstName(dto.getLoginDTO().getFirstName())
                        .lastName(dto.getLoginDTO().getLastName())
                        .enabled(dto.getLoginDTO().getEnabled())
                        .password(dto.getLoginDTO().getPassword())
                        .typeOfBusiness(dto.getLoginDTO().getTypeOfBusiness())
                        .build())
                .message(dto.getMessage())
                .subject(dto.getSubject())
                .build());
    }
}