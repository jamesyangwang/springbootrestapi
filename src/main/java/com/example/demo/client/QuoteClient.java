package com.example.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Quote;

@Component
public class QuoteClient {
	
    private static final Logger log = LoggerFactory.getLogger(QuoteClient.class);
    
    //https://stackoverflow.com/questions/22989500/is-resttemplate-thread-safe
    //https://spring.io/blog/2009/03/27/rest-in-spring-3-resttemplate
    //http://tiemensfamily.com/TimOnCS/2017/08/06/is-spring-resttemplate-thread-safe/
    @Autowired
    RestTemplate restTemplate;
    
    public Quote getQuote() {
    	return restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
    }

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());
    }
}
