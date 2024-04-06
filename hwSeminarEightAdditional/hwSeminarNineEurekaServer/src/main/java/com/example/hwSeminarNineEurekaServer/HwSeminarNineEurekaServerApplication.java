package com.example.hwSeminarNineEurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HwSeminarNineEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HwSeminarNineEurekaServerApplication.class, args);
	}

}
