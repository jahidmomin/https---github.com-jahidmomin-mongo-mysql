package com.poc.mongomysql.services.mongo;

import com.poc.mongomysql.model.mongo.Course;
import com.poc.mongomysql.model.mongo.UserCourse;
import com.poc.mongomysql.req.UserCourseReq;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    public void createCourse(Course course);

    public List<Course> getCourse();

    public Optional<Course> findById(long id);

    public Course update(Course course, long l);

    public void deleteCourseById(long id);

    public Course updatePartially(Course course, long id);

    public UserCourse order(UserCourseReq userCourseReq);
    public UserCourse uorder(UserCourseReq userCourseReq,String id);
}