package com.dnsloadbalance.interactionapi.config;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Value("${http-factory.connect-timeout}")
    private int connectTimeout;

    @Value("${http-factory.connection-request-timeout}")
    private int connectionRequestTimeout;

    @Value("${http-factory.read-timeout}")
    private int readTimeout;

    @Bean(name = "restTemplateComponents")
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
        clientHttpRequestFactory.setReadTimeout(readTimeout);

        return new RestTemplate(clientHttpRequestFactory);
    }

    @Bean(name = "restTemplateSimpleConnection")
    public RestTemplate restTemplateSimpleConnection() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        clientHttpRequestFactory.setReadTimeout(readTimeout);

        return new RestTemplate(clientHttpRequestFactory);
    }

    @Bean(name = "restTemplateManaged")
    public RestTemplate restTemplateManaged() {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();

        CloseableHttpClient client = HttpClients.custom()
                .setKeepAliveStrategy(connectionKeepAliveStrategy)
                .setConnectionManager(connManager)
                .build();

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(connectTimeout);
        httpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
        httpRequestFactory.setReadTimeout(readTimeout);
        httpRequestFactory.setHttpClient(client);

        return new RestTemplate(httpRequestFactory);
    }

    ConnectionKeepAliveStrategy connectionKeepAliveStrategy = new ConnectionKeepAliveStrategy() {
        @Override
        public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
            HeaderElementIterator elementIterator = new BasicHeaderElementIterator(httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while(elementIterator.hasNext()){
                HeaderElement element = elementIterator.nextElement();
                String value = element.getValue();
                if(value != null &&  "timeout".equalsIgnoreCase(element.getName())) {
                    return Long.parseLong(value) * 1000;
                }
            }
            return 5000;
        }
    };

}
