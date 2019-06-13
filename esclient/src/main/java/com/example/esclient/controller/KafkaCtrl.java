package com.example.esclient.controller;

import com.example.esclient.kafka.Receiver.KafkaReceiver;
import com.example.esclient.kafka.sender.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Majg on 2019-06-12
 **/
@RequestMapping("kafka")
@RestController
public class KafkaCtrl {

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private KafkaReceiver kafkaReceiver;

    @RequestMapping("send")
    public String send(){
        try {
            kafkaSender.send();
            return "已经发送";
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
    }

}

