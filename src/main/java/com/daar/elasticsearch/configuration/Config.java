package com.daar.elasticsearch.configuration;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.daar.elasticsearch.repository")
@ComponentScan(basePackages = "com.daar.elasticsearch")
public class Config extends AbstractElasticsearchConfiguration {
    private final static String ELASTICSEARCH_URL = "localhost:9200";

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(ELASTICSEARCH_URL)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
