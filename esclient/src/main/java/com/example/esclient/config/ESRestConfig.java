package com.example.esclient.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
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

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;

    private HostnameVerifier TRUSTED_VERIFIER = null;

    @Bean
    public RestClient getRestClient() {
        RestClient client = null;
        try {
            KeyStore truststore = KeyStore.getInstance("PKCS12");

            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "elastic-certificates.p12");
            String absolutePath = file.getAbsolutePath();

            try (InputStream is = Files.newInputStream(Paths.get(absolutePath))) {
                truststore.load(is, "".toCharArray());
            }
            SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(truststore, new TrustSelfSignedStrategy());
            final SSLContext sslcontext = sslBuilder.build();
            // 以上证书有效，但是证书签名是instance,必须设置hosts文件映射ip名才允许访问
            final CredentialsProvider credentialsProvider =
                    new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials("elastic", "123456"));

            // 配置hostName
            String[] split = clusterNodes.split(",");
            String[] split1 = split[0].split(":");

            RestClientBuilder builder = RestClient.builder(new HttpHost(split1[0], 9200, "https"))
                    .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                        @Override
                        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            httpClientBuilder.setSSLHostnameVerifier(getTrustedVerifier());
                            return httpClientBuilder.setSSLContext(sslcontext);
                        }
                    });
            client  = builder.build();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

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
