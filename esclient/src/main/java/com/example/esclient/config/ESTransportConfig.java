package com.example.esclient.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Majg on 2019-06-03
 **/
@Configuration
public class ESTransportConfig {
    @Value("${valib_srv.es_name:elasticsearch}")
    private String clusterName;

    @Value("${valib_srv.transport_es_nodes:127.0.0.1:9300}")
    private String clusterNodes;

    @Value("${valib_srv.is_SSL_es:false}")
    private Boolean is_ssl_es;

    @Value("${valib_srv.certPath:C:\\certs\\elastic-certificates.p12}")
    private String certPath;

    @Bean(name = "sslTransportClient")
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

    private Settings settings() throws FileNotFoundException {
        if (is_ssl_es) {
            Settings.Builder builder = Settings.builder();
            builder.put("cluster.name", clusterName);
            builder.put("xpack.security.user", "elastic:2019");
            builder.put("xpack.security.enabled", true);
            builder.put("xpack.security.transport.ssl.keystore.path", certPath);
            builder.put("xpack.security.transport.ssl.truststore.path", certPath);
            builder.put("xpack.security.transport.ssl.verification_mode", "certificate");
            builder.put("xpack.security.transport.ssl.enabled", true);
            builder.put("thread_pool.search.size", 10);//增加线程池个数，暂时设为10
            return builder.build();
        } else {
            Settings.Builder builder = Settings.builder();
            return builder.build();

        }
    }

    @Bean(name = "elasticsearchTemplate")
    public ElasticsearchTemplate getElasticSearchTemplate() throws Exception {
        return new ElasticsearchTemplate(getTransportClient());
    }
}
