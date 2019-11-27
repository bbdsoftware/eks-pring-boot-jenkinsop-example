package com.bbdsoftware.service.reference.api;

import com.bbdsoftware.service.config.exceptions.checked.*;
import com.bbdsoftware.service.reference.api.model.*;
import com.bbdsoftware.service.reference.services.*;
import com.bbdsoftware.service.response.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.*;
import org.flips.annotation.*;
import org.json.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
public class SampleApi  {

    private final DemoService demoService;

    @Autowired
    public SampleApi(@Qualifier("DemoServiceImpl") DemoService demoService) {
        this.demoService = demoService;
    }

    @ApiOperation(value = "This is the toggleExample", notes = "Notes on the swagger")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Data"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 500, message = "Error")})
    @GetMapping(value = "/v1/repos/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestApiResult toggleExample(@PathVariable("user") final String user) throws Exception {
        return new RestApiResult<String>().withResult(demoService.getReposForUserMethod(user));
    }

    @ApiOperation(value = "This is the toggleExample", notes = "Notes on the swagger")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Data"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 500, message = "Error")})
    @GetMapping(value = "/v2/repos/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    @FlipOnEnvironmentProperty(property = "service.feature.example", expectedValue = "true")
    public RestApiResult<String> toggleExamplev2(@PathVariable("user") final String user) throws Exception {
        return new RestApiResult<String>().withResult(demoService.getReposForUserMethod(user));
    }

    @ApiOperation(value = "This is the mysql db example", notes = "This one also returns a different HTTP Status Code")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Hello message created"),
            @ApiResponse(code = 400, message = "Bad Data"),
            @ApiResponse(code = 500, message = "Error")
    })
    @PostMapping(value = "/v1/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResult<String>> sayHello(@RequestBody(required=false) String data) {

        String name = null;
        try {
            name = (new JSONObject(data)).getString("name");
        } catch(JSONException e) {
            log.error("Could not extract name from request body: " + e.getMessage());
        }

        return new ResponseEntity<>(new RestApiResult<String>().withResult(demoService.sayHello(name)), HttpStatus.CREATED);

    }

    @ApiOperation(value = "Get the last 10 greetings")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 500, message = "Error")
    })
    @GetMapping(value = "/v1/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestApiResult<List<GreetingDTO>> history() {
        return new RestApiResult<List<GreetingDTO>>().withResult(demoService.history());
    }

    @ApiOperation(value = "This is the BBDHTTPBusinessException Example", notes = "Notes on the swagger")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Data"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 500, message = "Error")})
    @GetMapping(value = "/v1/BBDHTTPBusinessException", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestApiResult<String> testBBDHTTPBusinessException() throws BBDHttpBusinessException
    {
        throw new BBDHttpBusinessException("Oops!!", HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    }

    @ApiOperation(value = "This is the BBDHTTPServiceException Example", notes = "Notes on the swagger")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Data"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 500, message = "Error")})
    @GetMapping(value = "/v1/BBDHTTPServiceException", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestApiResult<String> testBBDHTTPServiceException() throws BBDHttpServiceException
    {
        throw new BBDHttpServiceException("Oops!!");
    }

    @ApiOperation(value = "This is the BBDHTTPTechnicalException Example", notes = "Notes on the swagger")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Data"),
            @ApiResponse(code = 404, message = "Not found "),
            @ApiResponse(code = 500, message = "Error")})
    @GetMapping(value = "/v1/BBDHTTPTechnicalException", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestApiResult<String> testBBDHTTPTechnicalException() throws BBDHttpInvalidRequestException
    {
        throw new BBDHttpInvalidRequestException("Oops!!");
    }
}
