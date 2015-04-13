package org.apache.geode.demo.fastfootshoes.dataloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
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
    
    @Bean
    CommandLineRunner loadData(final CacheLoader cl) {
    	return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				System.out.println("\n Loading the grid.\n");
				cl.loadData();
				System.out.println("\nPress Enter to continue.\n");
			    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			    try {
					bufferedReader.readLine();
				} catch (IOException e) {}
			}
    		
    	};
    }
    
}

