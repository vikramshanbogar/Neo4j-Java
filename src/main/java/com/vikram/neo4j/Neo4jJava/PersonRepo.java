package com.vikram.neo4j.Neo4jJava;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepo extends Neo4jRepository<Person, Integer> {

}
