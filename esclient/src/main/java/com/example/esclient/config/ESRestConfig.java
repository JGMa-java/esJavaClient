package com.example.esclient.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Created by Majg on 2019-06-03
 **/
@Configuration
public class ESRestConfig {

    private static Logger logger = LoggerFactory.getLogger(ESRestConfig.class);
    private int connectTimeOut = 1000;
    private int socketTimeOut = 30000;
    private int maxConnectNum = 100;
    private int maxConnectPerRoute = 100;
    private int connectionRequestTimeOut = 5000;

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;

    @Value("${valib_srv.is_SSL_es:false}")
    private Boolean is_SSL_es;

    @Value("${valib_srv.certPath:C:\\certs\\elastic-certificates.p12}")
    private String certPath;

    @Bean("restHighLevelClient")
    public RestClient RestClient() {
        RestClient restClient = null;
        String scheme = null;
        if (is_SSL_es) {
            scheme = "https";
        } else {
            scheme = "http";
        }

        // 配置hostName
        String[] split = clusterNodes.split(",");

        int length = split.length;
        HttpHost[] https = new HttpHost[length];

        for (int i = 0; i < length; i++) {

            String[] split1 = split[i].split(":");
            https[i] = new HttpHost(split1[0], Integer.valueOf(split1[1]), scheme);

        }

        RestClientBuilder builder = RestClient.builder(https)
                .setFailureListener(new RestClient.FailureListener() {
                    @Override
                    public void onFailure(Node node) {
                        HttpHost host = node.getHost();
                        logger.error("连接ES节点失败，host:{}", host);
                    }
                });
        //连接数配置
        setMutiConnectConfig(builder);
        //超时配置
        setRequestTimeOutConfig(builder);

        restClient = builder.build();
        return restClient;
    }

    /**
     * 异步httpclient的连接延时配置
     */
    public void setRequestTimeOutConfig(RestClientBuilder builder) {
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                requestConfigBuilder.setConnectTimeout(connectTimeOut);
                requestConfigBuilder.setSocketTimeout(socketTimeOut);
                requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
                return requestConfigBuilder;
            }
        });
    }

    /**
     * 异步httpclient的连接数配置
     */
    public void setMutiConnectConfig(RestClientBuilder builder) {
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                if (is_SSL_es) {
                    try {
                        KeyStore truststore = KeyStore.getInstance("PKCS12");

                        truststore.load(Files.newInputStream(Paths.get(certPath)), "".toCharArray());

                        SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(truststore, new TrustSelfSignedStrategy()).build();
                        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "hzqsy@2019"));

                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                        httpClientBuilder.setSSLHostnameVerifier(new HostnameVerifier() {
                            public boolean verify(String hostname, SSLSession session) {
                                return true;
                            }
                        });
                        httpClientBuilder.setSSLContext(sslcontext);
                        httpClientBuilder.setMaxConnTotal(maxConnectNum);
                        httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                    } catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException | KeyManagementException e) {
                        logger.error("配置ES加密集群连接数错误！-", e);
                        e.printStackTrace();
                    }
                } else {
                    httpClientBuilder.setMaxConnTotal(maxConnectNum);
                    httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                }
                return httpClientBuilder;
            }
        });
    }

}
