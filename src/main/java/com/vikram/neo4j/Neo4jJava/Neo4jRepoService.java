package com.vikram.neo4j.Neo4jJava;

import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Neo4jRepoService {

    @Autowired
    PersonRepo personRepo;
    public Neo4jRepoService(Driver driver) {
        this.driver = driver;
    }
    Driver driver;
    void batchInsert() {
        try (var session = driver.session()) {
            //Create a list of objects
            List<Map<String, Object>> listOfObjects = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                Map<String, Object> person = new HashMap<>();
                person.put("id", i);
                person.put("name", UUID.randomUUID()
                        .toString()
                        .substring(0, 10));
                person.put("age", new Random().nextInt(100));
                listOfObjects.add(person);
            }
            Map<String, Object> params = new HashMap<>();
            params.put("props", listOfObjects);
            long start = System.currentTimeMillis();
            session.writeTransaction((tx -> tx.run("UNWIND $props AS map CREATE (n:PERSONS) SET n=map", params).consume()));
            System.out.println("Time Taken to process and save 10000 records to Neo4j Batch Insert took "+ (System.currentTimeMillis() - start) + " ms");
        }
    }

    void persistDataToNeo4j(){
        List<Person> listOfObjects = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            listOfObjects.add(new Person(i,UUID.randomUUID()
                    .toString()
                    .substring(0, 10),new Random().nextInt(100)));
        }

        long start = System.currentTimeMillis();
        personRepo.saveAll(listOfObjects);
        System.out.println("Time Taken to process and save 10000 records to Neo4j took "+ (System.currentTimeMillis() - start) + " ms");
    }

}
