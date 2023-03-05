package nrifintech.busMangementSystem;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("nrifintech")
public class BusMangementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusMangementSystemApplication.class, args);
	}

}
