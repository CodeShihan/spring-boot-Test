package com.spring.ws.spring.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Bean(name = "restHighLevelClient")
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("127.0.0.1",9200,"http")
        ));
        return client;
    }

    @Bean
    public ElasticSearchUtil elasticSearchUtil(@Qualifier("restHighLevelClient")RestHighLevelClient client){
        return new ElasticSearchUtil(client,EsConst.Es_Index_Name);
    }


}
