package com.example.esclient.controller;

import com.example.esclient.pojo.Test1;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * Created by Majg on 2019-05-29
 **/
@RestController
@RequestMapping("/es")
public class JavaEsClientCtrl {

    @Autowired
    // springboot集成TransportClient
    public ElasticsearchTemplate elasticsearchTemplate;

    @Qualifier("sslTransportClient")
    @Autowired
    // es 9300端口
    public TransportClient transportClient;

    @Autowired
    // es 9200restful
    public RestClient restClient;

    @Qualifier("EsHttpClient")
    @Autowired
    public HttpClient httpClient;

    @RequestMapping(value = "test5", method = RequestMethod.GET)
    public String test5() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        String body = null;
        HttpGet httpGet = new HttpGet("https://127.0.0.1:9200/test1/doc/a001");
//        HttpGet httpGet = new HttpGet("https://192.168.8.205:9200/log_useroperate_topic-201905/information/430b338f45ec46cdaddc77970cdc721f");
        HttpResponse response = httpClient.execute(httpGet);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, "utf-8");
        }
        return body;
    }


    /**
     * 9200 restClient操作
     *
     * @return
     */
    @RequestMapping(value = "test3", method = RequestMethod.GET)
    public String test3() {
        try {
            // 查询
            Request request = new Request("GET", "/test1/doc/a004");
//            Request request = new Request("GET", "/log_useroperate_topic-201905/information/1a44c110dc3e467b9b58ef3c47d464ab");
//            request.setJsonEntity("{\n" +
//                    "\t\"first_name\": \"宝贝\",\n" +
//                    "\t\"last_name\": \"按住她\",\n" +
//                    "\t\"age\": 25\n" +
//                    "}");

            Response response = restClient.performRequest(request);
            String responseBody = EntityUtils.toString(response.getEntity());

            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * springboot集成es,template操作
     *
     * @return
     */
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2() {
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(matchQuery("LogTypeName", "用户操作日志")).build();
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("first_name", "宝贝")).build();

        // template需要pojo映射索引，此处没列举
        return elasticsearchTemplate.queryForList(searchQuery, Test1.class).toString();
    }

    /**
     * 9300 transportClient操作
     * 根据id查询
     */
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1(/*@RequestParam("id") String id*/) {
        try {

            BoolQueryBuilder builder = new BoolQueryBuilder();
            //builder.must(QueryBuilders.termQuery("age", 23));
            SearchResponse response = transportClient.prepareSearch("test1")//可添加多个index，逗号隔开
//            SearchResponse response = transportClient.prepareSearch("log_useroperate_topic-201905")//可添加多个index，逗号隔开
                    .setTypes("doc","information")//可添加多个type，逗号隔开
                    .setQuery(builder)
//                    .setFetchSource(new String[]{"first_name", "last_name", "age"}, null)//自定义返回的字段
                    .setFrom(0)
                    .setSize(5)
                    .setExplain(true)//按查询匹配度排序
                    .get();
            StringBuilder sb = new StringBuilder();
            for (SearchHit hit : response.getHits()) {
                sb.append(hit.getSourceAsString());
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public String test4(/*@RequestParam("id") String id*/) {
        try {

            BoolQueryBuilder builder = new BoolQueryBuilder();
            //builder.must(QueryBuilders.termQuery("age", 23));
            SearchResponse response = transportClient.prepareSearch("kafkatest")//可添加多个index，逗号隔开
//            SearchResponse response = transportClient.prepareSearch("log_useroperate_topic-201905")//可添加多个index，逗号隔开
                    .setTypes("doc","information")//可添加多个type，逗号隔开
                    .setQuery(builder)
//                    .setFetchSource(new String[]{"first_name", "last_name", "age"}, null)//自定义返回的字段
                    .setFrom(0)
                    .setSize(10)
                    .setExplain(true)//按查询匹配度排序
                    .get();
            StringBuilder sb = new StringBuilder();
            for (SearchHit hit : response.getHits()) {
                sb.append(hit.getSourceAsString());
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
