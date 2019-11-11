package com.qf.config;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {
    @Value("${solr.baseUrl}")
    private String baseUrl;
    @Bean
    public HttpSolrServer getHttpSolrServer(){
        return new HttpSolrServer(baseUrl);
    }
}
