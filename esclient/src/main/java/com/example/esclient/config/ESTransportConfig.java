package com.example.esclient.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Majg on 2019-06-03
 **/
@Configuration
public class ESTransportConfig {
    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;

    @Bean
    public ElasticsearchTemplate getEsTemplate() {

        try {
            return new ElasticsearchTemplate(getTransportClient());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


//    @Bean
//    //pem忽略证书验证
//    public TransportClient getTransportClient() throws FileNotFoundException, UnknownHostException {
//        TransportClient client = new PreBuiltXPackTransportClient(Settings.builder()
//                .put("cluster.name", clusterName)
//                .put("xpack.security.user", "elastic:123456")
//                .put("xpack.security.enabled", true)
//                .put("xpack.ssl.certificate_authorities", "D:\\develop\\elasticsearch-6.5.0-code\\elasticsearch-6.5.0\\config\\certs\\certificate-bundle\\ca\\ca.crt")
//                .put("xpack.ssl.key", "D:\\develop\\elasticsearch-6.5.0-code\\elasticsearch-6.5.0\\config\\certs\\certificate-bundle\\instance\\instance.key")
//                .put("xpack.ssl.certificate", "D:\\develop\\elasticsearch-6.5.0-code\\elasticsearch-6.5.0\\config\\certs\\certificate-bundle\\instance\\instance.crt")
//                .put("xpack.security.transport.ssl.verification_mode", "certificate")
//                .put("xpack.security.transport.ssl.enabled", "true")
//                .build());
//        String[] split = clusterNodes.split(",");
//        for (String s : split) {
//            String[] split1 = s.split(":");
//            int port = Integer.parseInt(split1[1]);
//            client.addTransportAddress(new TransportAddress(InetAddress.getByName(split1[0]), port));
//        }
//        return client;
//    }
    @Bean
    //pkcs12
    public TransportClient getTransportClient() throws FileNotFoundException {
        try {
            PreBuiltXPackTransportClient packTransportClient = new PreBuiltXPackTransportClient(settings());
            String[] split = clusterNodes.split(",");
            for (String s : split) {
                String[] split1 = s.split(":");
                int port = Integer.parseInt(split1[1]);
                packTransportClient.addTransportAddress(new TransportAddress(InetAddress.getByName(split1[0]), port));
            }

            return packTransportClient;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    //pkcs12
    private Settings settings() throws FileNotFoundException {

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "elastic-certificates.p12");
        String absolutePath = file.getAbsolutePath();
        Settings.Builder builder = Settings.builder();
        builder.put("cluster.name", clusterName);
        builder.put("xpack.security.user", "elastic:123456");
        builder.put("xpack.security.enabled", true);

        builder.put("xpack.security.transport.ssl.keystore.path", absolutePath);
        builder.put("xpack.security.transport.ssl.truststore.path", absolutePath);
        builder.put("xpack.security.transport.ssl.verification_mode", "certificate");

        builder.put("xpack.security.transport.ssl.enabled", true);
        return builder.build();
    }

}
