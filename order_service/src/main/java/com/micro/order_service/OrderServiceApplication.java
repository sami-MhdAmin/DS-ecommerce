package com.micro.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	//Here I’m going to call a product service endpoint in order service. For that we need RestTemplate for that.

	//Rest Template is used to create applications that consume RESTful Web Services. You can use the exchange(), getForObject etc methods to consume the web services for all HTTP methods.
	//The code given below shows how to create Bean for Rest Template to auto wiring the Rest Template object.

}
