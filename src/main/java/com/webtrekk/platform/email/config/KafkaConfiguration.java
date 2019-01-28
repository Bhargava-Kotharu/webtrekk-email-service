package com.webtrekk.platform.email.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import com.webtrekk.platform.email.constants.EmailApplicationConstants;

/**
 * Kafka Configuration
 * 
 * @author bkotharu
 *
 */
@Configuration
public class KafkaConfiguration {

	@Value(value = "${spring.kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic emailTopic() {
		return new NewTopic(EmailApplicationConstants.TOPIC_NAME, 1, (short) 1);
	}

}
