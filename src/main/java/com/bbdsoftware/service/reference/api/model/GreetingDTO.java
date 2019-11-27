package com.bbdsoftware.service.reference.api.model;

import lombok.*;

import java.time.*;

@Data
@AllArgsConstructor
public class GreetingDTO {

    private String message;
    private LocalDateTime greetDateTime;
}
