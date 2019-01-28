package com.webtrekk.platform.email;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.test.context.TestPropertySource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.webtrekk.platform.email.dto.EmailDTO;

@TestConfiguration
@TestPropertySource("classpath:application-test.properties")
public class EmailApplicationTestConfiguration {

	@Value(value = "${spring.kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Bean
	public ProducerFactory<String, EmailDTO> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, EmailDTO> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
	 @Bean
	   public MethodValidationPostProcessor bean() {
	      return new MethodValidationPostProcessor();
	   }
}
