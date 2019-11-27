package com.bbdsoftware.service.reference.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String message;
    private LocalDateTime greetDateTime;

    public Greeting(String message, LocalDateTime greetDateTime) {
        this.message = message;
        this.greetDateTime = greetDateTime;
    }

}
