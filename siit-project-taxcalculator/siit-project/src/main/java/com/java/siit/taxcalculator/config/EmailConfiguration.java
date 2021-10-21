package com.java.siit.taxcalculator.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Getter
@Setter
public class EmailConfiguration {

    private final String host ="smtp.mailtrap.io";
    private final int port = 2525;
    private final String username ="ff260f01528530";
    private final String password ="03b462328e3a89";

}
