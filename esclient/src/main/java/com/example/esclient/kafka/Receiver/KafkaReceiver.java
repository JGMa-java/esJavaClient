package com.example.esclient.kafka.Receiver;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Majg on 2019-06-12
 **/
@Component
public class KafkaReceiver {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    public ElasticsearchTemplate elasticsearchTemplate;

    @KafkaListener(topics = {"JGMa_kafka"}, groupId = "${kafk.aconsumer.group-id}")
    public void listen(List<ConsumerRecord<?, ?>> record, Acknowledgment ack) {


//        for (ConsumerRecord i : record) {
//            Optional<?> kafkaMessage = Optional.ofNullable(i.value());
//            if (kafkaMessage.isPresent()) {
//
//                Object message = kafkaMessage.get();
//
//                log.info("----------------- record =" + record);
//                log.info("------------------ message =" + message);
//            }
//        }



        try {
            final int batch_size = record.size();
            List<IndexQuery> queries = new ArrayList<>(batch_size);

            log.warn("一共消费到{}条消息",batch_size);

            for (ConsumerRecord i : record) {
                Optional<?> kafkaMessage = Optional.ofNullable(i.value());
                if (kafkaMessage.isPresent()) {

                    Object message = kafkaMessage.get();

                    log.info("----------------- record =" + record);
                    log.info("------------------ message =" + message);

                    IndexQuery indexQuery = new IndexQuery();
                    indexQuery.setIndexName("kafkatest");
                    indexQuery.setType("doc");
                    indexQuery.setId(UUID.randomUUID().toString());
                    indexQuery.setSource(message.toString());
                    queries.add(indexQuery);
                }

            }
            //es批量保存
            elasticsearchTemplate.bulkIndex(queries);

            //确认已经消费
            ack.acknowledge();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
