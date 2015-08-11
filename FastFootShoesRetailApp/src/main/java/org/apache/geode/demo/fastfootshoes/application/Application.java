package org.apache.geode.demo.fastfootshoes.application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:client-cache-listener.xml")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);	
	}
}