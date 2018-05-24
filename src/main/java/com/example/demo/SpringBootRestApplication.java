package com.example.demo;

import java.util.Locale;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.example.demo.model.Quote;

@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient
//@EnableAdminServer
//@EnableHystrix
//@EnableConfigServer
public class SpringBootRestApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootRestApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}

	@Bean
	public Validator validator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();
	}

	@Bean
	public LocaleResolver localeResolver() {
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}
}

//@RestController
//class ServiceInstanceRestController {
//	@Autowired
//	private DiscoveryClient discoveryClient;
//	@GetMapping("/service-instance/{applicationName}")
//	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
//		return this.discoveryClient.getInstances(applicationName);
//	}
//}



//http://localhost:8080/actuator
//http://localhost:8080	->	http://localhost:8080/browser/index.html#/	->	Explorer: /actuator







//https://github.com/komoot/swagger-springmvc
//https://github.com/springfox/springfox/issues/1307




//versioning:
//header
//parameter
//uri


















//https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html
//https://spring.io/blog/2015/03/18/spring-boot-support-in-spring-tool-suite-3-6-4
//https://stackoverflow.com/questions/37490144/is-there-a-difference-between-run-as-spring-boot-app-and-run-as-java-applicati

//open “spring-boot-autoconfigure-1.2.2.RELEASE.jar” (under “Maven Dependencies”) and
//browse to “META-INF/spring-configuration-metadata.json”.
//You’ll find properties like server.port being documented there.


//https://spring.io/guides/gs/rest-service/
//https://spring.io/guides/gs/consuming-rest/









