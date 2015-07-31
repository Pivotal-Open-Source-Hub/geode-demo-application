package com.gopivotal.ce.gemfire;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.gemstone.gemfire.cache.CacheLoader;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@ImportResource("classpath:cache-config.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    CommandLineRunner loadData(final CacheLoader cl) {
    	return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				//cl.loadData();
			}
    		
    	};
    }
    
}
