package com.vikram.neo4j.Neo4jJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Neo4jJavaApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Neo4jJavaApplication.class, args);
        context.getBean(Neo4jRepoService.class).batchInsert();
        context.getBean(Neo4jRepoService.class).persistDataToNeo4j();
    }

}
