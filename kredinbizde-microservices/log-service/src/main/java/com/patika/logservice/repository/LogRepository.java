package com.patika.logservice.repository;

import com.patika.logservice.model.ExceptionLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<ExceptionLog, Integer> {
}
