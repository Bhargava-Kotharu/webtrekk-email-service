package com.webtrekk.platform.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * 
 * @author bkotharu
 *
 */
@EnableKafka
@SpringBootApplication
@EnableAutoConfiguration
public class WebtrekkEmailApplication {

	/**
	 * Entry point for the Webtrekk Email Application
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(WebtrekkEmailApplication.class, args);
	}
}
