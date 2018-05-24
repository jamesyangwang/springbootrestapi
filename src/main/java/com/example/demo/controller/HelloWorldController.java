package com.example.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

//	@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World!";
	}
	
	//localhost:8080/hello-world-internationalized
	//Accept-Language: zh_cn
	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
//	@GetMapping(path="/hello-world-internationalized")
//	public String helloWorldInternationalized() {
//		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
//	}

	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Bean!");
	}

	@GetMapping(path="/hello-world/path-variable/{name}")
	//http://localhost:8080/hello-world/path-variable/James
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello, %s!", name));
	}
}
