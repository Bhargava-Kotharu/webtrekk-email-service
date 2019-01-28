package com.webtrekk.platform.email.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import com.webtrekk.platform.email.constants.EmailApplicationConstants;

/**
 * Configuration class for listener
 * 
 * @author bkotharu
 *
 */
@Configuration
public class KafkaListenerConfiguration {

	@Value(value = "${spring.kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Value(value = "${spring.kafka.max.redelivery.attempts}")
	private int maxRetryAttempts;

	@Value(value = "${spring.kafka.max.redelivery.interval}")
	private long retryInterval;

	@Bean(name = "consumerFactory")
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, EmailApplicationConstants.GROUP_NAME);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.webtrekk.platform.email.dto");
		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public RetryPolicy retryPolicy() {
		SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
		simpleRetryPolicy.setMaxAttempts(maxRetryAttempts);
		return simpleRetryPolicy;
	}

	@Bean
	public FixedBackOffPolicy backOffPolicy() {
		FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
		backOffPolicy.setBackOffPeriod(retryInterval);
		return backOffPolicy;
	}

	@Bean(name = "retryTemplate")
	public RetryTemplate retryTemplate() {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setRetryPolicy(retryPolicy());
		retryTemplate.setBackOffPolicy(backOffPolicy());
		return retryTemplate;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
			@Qualifier("consumerFactory") ConsumerFactory<String, String> consumerFactory,
			@Qualifier("retryTemplate") RetryTemplate retryTemplate) {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		factory.setRetryTemplate(retryTemplate);
		return factory;
	}
}
