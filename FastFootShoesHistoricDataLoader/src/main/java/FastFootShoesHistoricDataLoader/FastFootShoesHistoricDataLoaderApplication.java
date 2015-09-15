package FastFootShoesHistoricDataLoader;

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
public class FastFootShoesHistoricDataLoaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastFootShoesHistoricDataLoaderApplication.class, args);
    }
    
    @Bean
    CommandLineRunner loadData(final CacheLoader cl) {
    	return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				System.out.println("**********Loading the grid**********");
				cl.loadData();
				System.out.println("**********Loading Completed**********");
				System.out.println("**********Press Enter To Continue**********");
			    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			    try {
					bufferedReader.readLine();
				} catch (IOException e) {}
			}
    		
    	};
    }
    
}
