package com.abc.bleeter.bleeterbackend.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import java.util.Collections

@Configuration
@EnableMongoRepositories(basePackages = ["com.abc.bleeter.bleeterbackend"])
class MongoConfig : AbstractMongoClientConfiguration() {

    val converters: List<Converter<Any, Any>> = ArrayList<Converter<Any, Any>>();

    override fun getDatabaseName(): String {
        return "bleeterDatabase";
    }

    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString("mongodb://localhost:27017/bleeterDatabase");
        val mongoClientSettings : MongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
        return MongoClients.create(mongoClientSettings);
    }

    override fun getMappingBasePackages(): Collection<String> {
        return Collections.singleton("com.abc.bleeter.bleeterbackend");
    }

}