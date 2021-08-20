package com.leviosa.pumpkin.gateway;

import com.leviosa.pumpkin.filters.AddUserIdFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class Application {
	@Bean
	public AddUserIdFilter addUserIdFilter() {
		return new AddUserIdFilter();
	}
    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}