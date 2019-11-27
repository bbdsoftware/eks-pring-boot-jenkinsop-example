package com.bbdsoftware.service.reference.services;

import com.bbdsoftware.service.reference.api.model.*;

import java.util.*;

public interface DemoService {

    String getReposForUserMethod(String user) throws Exception;

    String sayHello(String name);

    List<GreetingDTO> history();

}
