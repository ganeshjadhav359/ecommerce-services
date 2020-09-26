package com.ganeshjadhav359.ecommercemicroservices.ecommgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class EcommGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommGatewayApplication.class, args);
	}

}
