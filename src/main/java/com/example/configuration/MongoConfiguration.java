package com.example.configuration;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    @Bean
    public MongoTemplate mongoTemplate(EmbededMongoDb embededMongoDb) {
        return new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, embededMongoDb.getLocalhost(),
                embededMongoDb.getPort())), "test");
    }


}
