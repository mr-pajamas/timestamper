package org.nelect.timestamper.internal.sms;

import javax.net.ssl.SSLContext;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Michael on 2016/6/28.
 */
@Configuration
public class MessageConfig {

    @Bean(destroyMethod = "close")
    public CloseableHttpClient httpClient() throws Exception {
        return HttpClients.custom()
            .setConnectionManager(connectionManager())
            .setConnectionManagerShared(true)
            .build();
    }

    @Bean(destroyMethod = "shutdown")
    public HttpClientConnectionManager connectionManager() throws Exception {
        SSLContext sslContext = SSLContexts.custom()
            .loadTrustMaterial(TrustSelfSignedStrategy.INSTANCE)
            .build();

        SSLConnectionSocketFactory sslConnectionSocketFactory =
            new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> socketFactoryRegistry =
            RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslConnectionSocketFactory)
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .build();

        return new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    }

    @Bean
    public RestTemplate restTemplate() throws Exception {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient()));
    }
}
