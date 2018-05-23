package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.Quote;

@FeignClient(url="${quote.service.url}", name="${quote.service.name}")
public interface QuoteFeignClient {
	
	@RequestMapping(method=RequestMethod.GET, path="${quote.service.path}")
	Quote getQuote();
}















































//https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html
