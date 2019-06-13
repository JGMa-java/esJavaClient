package com.example.esclient.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Majg on 2019-06-12
 **/
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String kafka_servers;
    @Value("${kafka.consumer.enable-auto-commit}")
    private String consumer_enable_auto_commit;
    @Value("${kafka.consumer.auto-commit-interval}")
    private String consumer_auto_commit_interval;
    @Value("${kafka.consumer.auto-offset-reset}")
    private String consumer_auto_offset_reset;

    @Value("${kafka.producer.key-serializer}")
    private String consumer_key_deserializer;
    @Value("${kafka.producer.value-serializer}")
    private String consumer_value_deserializer;

    @Value("${kafka.consumer.batch.listener}")
    private Boolean consumer_batch_listener;

    @Value("${kafka.consumer.batch.max.pool.interval.ms}")
    private Integer consumer_batch_max_pool_interval_ms;

    @Value("${kafka.consumer.batch.max.pool.records}")
    private Integer consumer_batch_max_pool_records;
    @Value("${kafka.consumer.session.timeout}")
    private String consumer_session_timeout;

    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka_servers);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumer_enable_auto_commit);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumer_auto_commit_interval);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, consumer_session_timeout);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumer_key_deserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, consumer_value_deserializer);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, consumer_batch_max_pool_records);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, consumer_batch_max_pool_interval_ms);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumer_auto_offset_reset);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> properties = consumerConfigs();
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(consumer_batch_listener);
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);//设置提交偏移量的方式
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);//设置提交偏移量的方式
        return factory;
    }

}
