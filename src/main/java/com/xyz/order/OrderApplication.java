package com.xyz.order;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);

	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.xyz.order")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private static ApiInfo apiInfo() {
		return new ApiInfo("Order Microservice",
				"Order Microservice allows the manager or admin to manage orders created by them."
						+ "Each orders holds the information relevant to the order such as product details, status of the order, parcel details."
						+ "However once an order is created, the status of the order or order item can be update by providing relevent information to the api."
						+ " Following are the api's which are part of the Order microservice\r\n",

				"1.0.0", null, null, null, null, Collections.emptyList());
	}

}
