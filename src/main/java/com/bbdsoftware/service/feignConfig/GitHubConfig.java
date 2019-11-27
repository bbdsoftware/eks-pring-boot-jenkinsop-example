package com.bbdsoftware.service.feignConfig;

import com.bbdsoftware.service.config.exceptions.feign.*;
import feign.*;
import feign.codec.*;
import org.springframework.context.annotation.*;

@Configuration
public class GitHubConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new BBDFeignDefaultErrorDecoder();
    }

}
