package com.bbdsoftware.service.reference.repository;



import com.bbdsoftware.service.reference.repository.entities.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface GreetingRepository extends CrudRepository<Greeting, Long> {

    List<Greeting> findTop10ByOrderByGreetDateTimeDesc();

}
