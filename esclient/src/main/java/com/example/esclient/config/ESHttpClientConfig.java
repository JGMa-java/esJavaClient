package com.example.esclient.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;

/**
 * Created by Majg on 2019-06-04
 **/
@Configuration
public class ESHttpClientConfig {
    private HostnameVerifier TRUSTED_VERIFIER = null;
//    // httpClient连接池
//    public static PoolingHttpClientConnectionManager clientConnectionManager = null;
//
//    private int maxTotal = 50;
//
//    private int defaultMaxPerRoute = 25;
//
//    private ESHttpClientConfig() {
//        clientConnectionManager.setMaxTotal(maxTotal);
//        clientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
//    }

    @Bean(name = "EsHttpClient")
    public HttpClient getHttpClient() throws Exception {
        KeyStore truststore = KeyStore.getInstance("PKCS12");

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "elastic-certificates.p12");
        String absolutePath = file.getAbsolutePath();

        try (InputStream is = Files.newInputStream(Paths.get(absolutePath))) {
            truststore.load(is, "".toCharArray());
        }
        SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(truststore, new TrustSelfSignedStrategy());

        // 获取证书
        SSLContext sslcontext = sslBuilder.build();

        // 以上证书有效，但是证书签名是instance,必须设置hosts文件映射ip名才允许访问
        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "123456"));

        // 设置协议http和https对应的处理socket链接工厂的对象
//        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", PlainConnectionSocketFactory.INSTANCE)
//                .register("https", new SSLConnectionSocketFactory(sslcontext))
//                .build();
//        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        //创建自定义的httpclient对象
        CloseableHttpClient client = HttpClients.custom()
                //.setConnectionManager(connManager)
                .setDefaultCredentialsProvider(credentialsProvider)
                .setSSLHostnameVerifier(new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .setSSLContext(sslcontext)
                .build();
        return client;
    }

    // 跳过证书签名
    private HostnameVerifier getTrustedVerifier() {
        if (TRUSTED_VERIFIER == null)
            TRUSTED_VERIFIER = new HostnameVerifier() {

                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
        return TRUSTED_VERIFIER;
    }

}
