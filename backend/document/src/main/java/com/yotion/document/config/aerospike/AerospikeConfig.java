package com.yotion.document.config.aerospike;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.WritePolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * File is created by andreychernenko at 07.07.2025
 */

@Configuration
public class AerospikeConfig {

    @Value("${spring.datasource.namespace}")
    private String namespace;
    @Value("${spring.datasource.host}")
    private String host;
    @Value("${spring.datasource.port}")
    private Integer port;

    @Bean
    public AerospikeClient aerospikeClient() {
        return new AerospikeClient(host, port);
    }

    @Bean
    public String getNamespace(){
        return namespace;
    }

}
