package com.example.demo.controller;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.QuoteClient;
import com.example.demo.model.Greeting;
import com.example.demo.model.Person;
import com.example.demo.model.Quote;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
	
	@Autowired
	QuoteClient qc;
	
	@Autowired
	Validator validator;
    
	//=========================================================================
    // validate with @Valid
    // errors will be handled by handleMethodArgumentNotValid() in ResponseEntityExceptionHandler
    // http://localhost:8080/person
    // Content-Type: application/json
    // {"name": "James", "licensePlate": "test"}

    @PostMapping("/person")
    public ResponseEntity<Greeting> person(@Valid @RequestBody Person person) {
    	Quote quote = qc.getQuote();
    	Greeting greeting = new Greeting(counter.incrementAndGet(), person.getName(), quote);
    	logger.info(greeting.toString());
    	
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Greeting>(greeting, responseHeaders, HttpStatus.OK);
    }

	//=========================================================================
	// validate with validator
	// throws out ConstraintViolation will be caught by @ExceptionHandler in ResponseEntityExceptionHandler
	// http://localhost:8080/greeting?name=James
	
    @RequestMapping(method=RequestMethod.GET, path="/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) throws Exception {
    	Greeting greeting = null;
    	try {
        	MDC.put("uuid", "counter_" + counter);
        	Quote quote = qc.getQuote();
        	greeting = new Greeting(counter.incrementAndGet(), String.format(template, name), quote);

        	Set<ConstraintViolation<Greeting>> violations = validator.validate(greeting);
        	if (!violations.isEmpty()) {
           		throw new ConstraintViolationException(violations);
        	}
        	
        	logger.info(greeting.toString());
        	logger.info("Greeting starts...");
    	} finally {
        	MDC.remove("uuid");
    	}
    	return greeting;
    }
    
	//=========================================================================

    @PostMapping(value = "/person2", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseEntity<String> person2(@Valid @RequestBody Person person, BindingResult bindingResult) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
		return bindingResult.hasErrors() ?
				new ResponseEntity<String>(bindingResult.getFieldError().getDefaultMessage(), responseHeaders, HttpStatus.BAD_REQUEST) :
					new ResponseEntity<String>(person.toString(), responseHeaders, HttpStatus.OK);
	}
    
	//=========================================================================

    @GetMapping("/cors")
    public ResponseEntity<Greeting> cors(@RequestParam String name) {
    	Quote quote = qc.getQuote();
    	@Valid Greeting greeting = new Greeting(counter.incrementAndGet(), name, quote);
    	logger.info(greeting.toString());
    	
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Greeting>(greeting, responseHeaders, HttpStatus.OK);
    }
    
	//=========================================================================
    //http://localhost:8080/person?name=James

    @GetMapping("/person")
    public ResponseEntity<Person> getPerson(@RequestParam String name) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Person>(new Person(name, "AAA"), responseHeaders, HttpStatus.OK);
    }

}





























//Test URL:
//http://localhost:8080/greeting?name=James


//Validation:
//-----------
//http://beanvalidation.org/2.0/
//http://hibernate.org/validator/
//http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints
//http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-configuring-validator-factory

//https://my.oschina.net/chen1988/blog/1511992
//http://www.baeldung.com/spring-data-rest-validators
//https://howtodoinjava.com/spring/spring-boot2/spring-rest-request-validation/
//https://blog.codecentric.de/en/2017/11/dynamic-validation-spring-boot-validation/
//https://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-adding-validation-to-a-rest-api/
//http://www.bbenson.co/post/spring-validations-with-examples/
//https://dzone.com/articles/implementing-validation-for-restful-services-with
//http://www.springboottutorial.com/spring-boot-validation-for-rest-services
//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://lmonkiewicz.com/programming/get-noticed-2017/spring-boot-rest-request-validation/
//https://github.com/in28minutes/spring-boot-examples/tree/master/spring-boot-2-rest-service-validation
//https://www.youtube.com/watch?v=wTuUMlKcno0


//Info:
//https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html
//https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html


//Swagger:
//https://api.swaggerhub.com/apis/jamesyangwang
//http://petstore.swagger.io/?url=https://api.swaggerhub.com/apis/jamesyangwang/CORS/0.1
//http://petstore.swagger.io/?url=https://api.swaggerhub.com/apis/jamesyangwang/Greeting/0.1
