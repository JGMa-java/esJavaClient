package com.example.esclient.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created By admin on 2020.02.12
 */
@Component
public class EsUtil {

    private static Logger logger = LoggerFactory.getLogger(EsUtil.class);

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestClient restHighLevelClient;

    private static RestClient client;

    /**
     * @PostContruct是spring框架的注解 spring容器初始化的时候执行该方法
     */
    @PostConstruct
    public void init() {
        logger.info("初始化ESUtil");
        client = this.restHighLevelClient;
    }

    public static JSONObject search(String method, String indexName, String dsl) {

        JSONObject jsonObject = null;
        Request request = new Request(method, indexName + "/_search");
        try {
            request.setJsonEntity(dsl);
            Response response1 = client.performRequest(request);
            String result = EntityUtils.toString(response1.getEntity(), "UTF-8");
            jsonObject = JSON.parseObject(result);
        } catch (IOException e) {
            logger.error("请求ES错误！-", e);
            e.printStackTrace();
        }
        return jsonObject;
    }

}
