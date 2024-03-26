package ru.gb.hwSeminarTwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("ru.gb.hwSeminarTwo.configuration")
public class HwSeminarTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HwSeminarTwoApplication.class, args);
	}

}
