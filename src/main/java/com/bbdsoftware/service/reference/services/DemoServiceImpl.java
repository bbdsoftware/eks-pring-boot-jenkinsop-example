package com.bbdsoftware.service.reference.services;

import com.bbdsoftware.service.config.exceptions.checked.*;
import com.bbdsoftware.service.config.logging.*;
import com.bbdsoftware.service.reference.api.model.*;
import com.bbdsoftware.service.reference.clients.*;
import com.bbdsoftware.service.reference.configuration.*;
import com.bbdsoftware.service.reference.repository.*;
import com.bbdsoftware.service.reference.repository.entities.*;
import io.micrometer.core.annotation.*;
import lombok.extern.slf4j.*;
import org.flips.annotation.*;
import org.flips.exception.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.logging.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Service
@Qualifier("DemoServiceImpl")
@Slf4j
public class DemoServiceImpl implements DemoService {

    private final GitHubClient gitHubClient;
    private final ReferenceConfiguration referenceConfiguration;
    private final GreetingRepository greetingRepository;


    @Autowired
    public DemoServiceImpl(GitHubClient gitHubClient, ReferenceConfiguration referenceConfiguration, GreetingRepository greetingRepository) {
        this.gitHubClient = gitHubClient;
        this.referenceConfiguration = referenceConfiguration;
        this.greetingRepository = greetingRepository;
    }

    /**
     * "@FlipOnEnvironmentProperty" here shows a feature toggle that will enable of disable a method based on a property in the config, if not matched
     * a FeatureNotEnabledException
     * <p>
     * " @Loggable" will log the method log and args , see other parameters to log entry or use warnings
     * <p>
     * "@@Timed will mark this method fro metric collection for micrometer and expose this ont he prometheus endpoint at /actuator/prometheus for scarping"
     * Recommend eyeballing distribution
     *
     * @param user User ID
     * @return Sting The Repo name
     */
    @Override
    @Timed(histogram = true)
    @Loggable(value = LogLevel.INFO, name = "getReposForUserMethod")
    @FlipOnEnvironmentProperty(property = "service.feature.repos", expectedValue = "true")
    public String getReposForUserMethod(String user) throws FeatureNotEnabledException, BBDHttpServiceException, BBDHttpInvalidRequestException, BBDHttpAuthException {

        return gitHubClient.repos(user, "bob")
                .stream()
                .map(GitHubClient.Repository::getName)
                .findFirst()
                .orElse("");

    }

    @Override
    @Timed(histogram = true)
    @Loggable(value = LogLevel.INFO, name = "sayHello")
    public String sayHello(String name) {

        if(name == null) {
            name = referenceConfiguration.getDefaultName();
        }

        // Using injected log variable from @Slf4j
        log.info("Saying hello to {}", name);

        Greeting greeting = greetingRepository.save(new Greeting("Hello, " + name, LocalDateTime.now()));

        return greeting.getMessage();
    }

    @Override
    @Timed(histogram = true)
    @Loggable(value = LogLevel.INFO, name = "history")
    public List<GreetingDTO> history() {

        List<GreetingDTO> result = new ArrayList<>();
        for(Greeting greeting: greetingRepository.findTop10ByOrderByGreetDateTimeDesc()) {
            result.add(new GreetingDTO(greeting.getMessage(), greeting.getGreetDateTime()));
        }

        return result;
    }
}
