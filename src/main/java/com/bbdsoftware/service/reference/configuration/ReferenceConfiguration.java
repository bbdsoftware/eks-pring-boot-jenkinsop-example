package com.bbdsoftware.service.reference.configuration;

import lombok.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@Configuration
@EnableConfigurationProperties(ReferenceConfiguration.class)
@ConfigurationProperties(prefix = "bbdsoftware.reference")
@Data
public class ReferenceConfiguration {

    // Automatically finds property "default-name"
    private String defaultName;

}
