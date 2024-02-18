package com.poc.mongomysql.repo.mongo;

import com.poc.mongomysql.model.mongo.UserCourse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepo extends MongoRepository<UserCourse,String> {

}
