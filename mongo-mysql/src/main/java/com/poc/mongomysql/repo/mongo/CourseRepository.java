package com.poc.mongomysql.repo.mongo;

import com.poc.mongomysql.model.mongo.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CourseRepository extends MongoRepository<Course,Long> {
}