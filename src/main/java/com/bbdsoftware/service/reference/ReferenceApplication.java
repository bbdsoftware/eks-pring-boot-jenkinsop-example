package com.bbdsoftware.service.reference;

import com.bbdsoftware.service.config.exceptions.*;
import com.bbdsoftware.service.config.logging.*;
import com.bbdsoftware.service.config.swagger.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.openfeign.*;

@SpringBootApplication
@EnableBBDSwagger
@EnableBBDExceptions
@EnableFeignClients
@EnableLogger
public class ReferenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReferenceApplication.class, args);
    }

}
