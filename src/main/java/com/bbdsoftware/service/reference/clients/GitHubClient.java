package com.bbdsoftware.service.reference.clients;

import com.bbdsoftware.service.feignConfig.*;
import com.bbdsoftware.service.config.exceptions.checked.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "GitHubClient", url = "${service.props.clients.github.baseUrl}", configuration = GitHubConfig.class)
public interface GitHubClient {
     class Repository {
        String name;

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }
     }

    @GetMapping("/users/{username}/repos")
    List<Repository> repos(@RequestHeader("Authorization ") String clientid,
                           @PathVariable("username") String owner) throws BBDHttpAuthException, BBDHttpServiceException, BBDHttpInvalidRequestException;
}



