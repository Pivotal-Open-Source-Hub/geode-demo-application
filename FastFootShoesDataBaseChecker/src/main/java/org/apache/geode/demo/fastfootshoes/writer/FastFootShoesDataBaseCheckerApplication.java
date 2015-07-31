package org.apache.geode.demo.fastfootshoes.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class FastFootShoesDataBaseCheckerApplication implements
		CommandLineRunner {

	private static final Logger log = LoggerFactory
			.getLogger(FastFootShoesDataBaseCheckerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FastFootShoesDataBaseCheckerApplication.class,
				args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {
		log.info("============================================================");
		log.info("Query the write through table");
		log.info("============================================================");
		String sql = "SELECT * FROM message_table";
		List<Message> messages = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Message>(Message.class));
		if (messages == null) {
			log.info("The results from the DB were null: " + sql);
		}
		if (messages.isEmpty()) {
			log.info("There were no records in the DB with query: " + sql);
		} else {
			log.info("****************************************************");
			log.info("Here are the results:");
			log.info("****************************************************");
			for (Message message : messages) {
				log.info(message.toString());
			}
			log.info("****************************************************");
		}
	}

}
