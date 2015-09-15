package org.apache.geode.demo.fastfootshoes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:client-cache.xml")
public class FastFootShoesTransactionSimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastFootShoesTransactionSimulatorApplication.class, args);
    }
    
    @Bean
    CommandLineRunner loadData(final CacheLoader cl) {
    	return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				cl.loadData();
			    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			    try {
					bufferedReader.readLine();
				} catch (IOException e) {}
			}
    		
    	};
    }
}
