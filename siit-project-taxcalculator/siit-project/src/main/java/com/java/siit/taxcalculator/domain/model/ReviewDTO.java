package com.java.siit.taxcalculator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private long id;

    private String email;

    private String name;

    private String subject;

    private String message;

    private LoginDTO loginDTO;
}
