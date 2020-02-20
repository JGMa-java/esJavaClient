package com.example.esclient.kafka.Receiver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Majg on 2019-06-12
 **/
@Component
public class KafkaReceiver {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    public ElasticsearchTemplate elasticsearchTemplate;

    @KafkaListener(topics = "${dps.valib.kafka.topic:valib}", groupId = "${dps.valib.kafka.groupId.elasticsearch:es_valib}")
    public void listenTl(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {

        System.out.println("共接收到消息："+records.size());
    }


    //@KafkaListener(topics = {"JGMa_kafka"}, groupId = "${kafk.aconsumer.group-id}")
    public void listen(List<ConsumerRecord<?, ?>> record, Acknowledgment ack) {

        try {
            final int batch_size = record.size();
            List<IndexQuery> queries = new ArrayList<>();

            for (ConsumerRecord i : record) {
                Optional<?> kafkaMessage = Optional.ofNullable(i.value());
                if (kafkaMessage.isPresent()) {

                    Object message1 = kafkaMessage.get();

                    final JSONObject message = JSON.parseObject(message1.toString());
                    //添加存入es时间
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format = dateFormat.format(new Date());
                    message.put("CreateTime", format);
                    dateFormat = null;

                    IndexQuery indexQuery = new IndexQuery();
                    indexQuery.setIndexName("kafkatest");
                    indexQuery.setType("doc");
                    //indexQuery.setId(UUID.randomUUID().toString());
                    indexQuery.setSource(message.toString());
                    queries.add(indexQuery);
                }

            }
            //es批量保存
            elasticsearchTemplate.bulkIndex(queries);
            log.info("一共消费了{}条数据，存储了{}条数据",batch_size,queries.size());
            //确认已经消费
            //ack.acknowledge();

        } catch (Exception e) {
            log.error("es批量存储失败");
            e.printStackTrace();
        }
    }
}
