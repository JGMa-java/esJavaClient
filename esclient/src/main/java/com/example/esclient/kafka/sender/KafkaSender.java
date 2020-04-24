package com.example.esclient.kafka.sender;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.esclient.pojo.Message;
import com.example.esclient.pojo.VehicleVideoToll;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Majg on 2019-06-12
 **/
@Component
public class KafkaSender {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //发送消息方法
    public void send() {

        String sql = "select * from rec_vehiclevideotoll20200417";
        RowMapper<VehicleVideoToll> rm = BeanPropertyRowMapper.newInstance(VehicleVideoToll.class);
        List<VehicleVideoToll> query = namedParameterJdbcTemplate.query(sql, rm);
        for (VehicleVideoToll vehicleVideoToll : query) {
            String jsonString = JSON.toJSONString(vehicleVideoToll, SerializerFeature.WriteMapNullValue);
            kafkaTemplate.send("vehiclevideotoll", jsonString);
        }


    }

}
