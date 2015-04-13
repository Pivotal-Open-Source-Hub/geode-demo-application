package org.apache.geode.demo.fastfootshoes.application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@ImportResource("classpath:client-cache.xml")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}

}