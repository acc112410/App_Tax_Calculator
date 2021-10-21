package com.java.siit.taxcalculator.controller;


import com.java.siit.taxcalculator.domain.entity.ReviewEntity;
import com.java.siit.taxcalculator.domain.model.ReviewDTO;
import com.java.siit.taxcalculator.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class Review {

    private final ReviewService reviewService;
    private final ReviewService service;

    @GetMapping("/review")
    public List<ReviewEntity> getAllReviews(){
        return service.getAllReviews();
    }

    @GetMapping("/test")
    public String test(){
        return "TEST";
    }

    @PostMapping
    public ReviewEntity createReview(@RequestBody ReviewDTO dto) {
        return reviewService.createReview(dto);
    }

}
