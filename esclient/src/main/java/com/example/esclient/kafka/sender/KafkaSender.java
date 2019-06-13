package com.example.esclient.kafka.sender;

import com.alibaba.fastjson.JSON;
import com.example.esclient.pojo.Message;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Majg on 2019-06-12
 **/
@Component
public class KafkaSender {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private KafkaTemplate kafkaTemplate;

    //发送消息方法
    public void send() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg("消息内容：" + simpleDateFormat.format(new Date()));
        message.setSendTime(new Date());
        log.info("sender已发送信息。。。");
        kafkaTemplate.send("JGMa_kafka", JSON.toJSONString(message));
    }

}
