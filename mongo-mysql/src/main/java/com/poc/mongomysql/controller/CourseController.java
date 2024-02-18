package com.poc.mongomysql.controller;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.poc.mongomysql.model.mongo.Course;
import com.poc.mongomysql.repo.mongo.CourseRepository;
import com.poc.mongomysql.repo.mongo.UserCourseRepo;
import com.poc.mongomysql.req.UserCourseReq;
import com.poc.mongomysql.services.mongo.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/course"})
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getcourseById(@PathVariable("id") long id) {
        System.out.println("Fetching course with id " + id);
        Optional<Course> optcourse;
        try {
            optcourse = courseService.findById(id);
            return new ResponseEntity<Course>(optcourse.get(), HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(value = "/create", headers = "Accept=application/json")
    public ResponseEntity<?> createcourse(@RequestBody Course course, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating course " + course.getName());
        try {
            courseService.createCourse(course);
        }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/course/{id}").buildAndExpand(course.getId()).toUri());

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/get", headers = "Accept=application/json")
    public List<Course> getAllcourse() {
        List<Course> tasks = courseService.getCourse();
        return tasks;
    }

    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<Course> deletecourse(@PathVariable("id") long id) {
        Optional<Course> optcourse = courseService.findById(id);
        Course course = optcourse.get();
        if (course == null) {
            return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
        }
        courseService.deleteCourseById(id);
        return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<Course> updatecoursePartially(@PathVariable("id") long id, @RequestBody Course currentcourse) {
        Optional<Course> optcourse = courseService.findById(id);
        Course course = optcourse.get();
        if (course == null) {
            return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
        }
        Course usr = courseService.updatePartially(currentcourse, id);
        return new ResponseEntity<Course>(usr, HttpStatus.OK);
    }


    @PatchMapping(value = "/purchase", headers = "Accept=application/json")
    public ResponseEntity<?> purchaseCourses(@RequestBody UserCourseReq userCourseReq) {
        courseService.order(userCourseReq);
        return ResponseEntity.ok("Successfully Ordered");
    }

    @PatchMapping(value = "/upurchase/{id}", headers = "Accept=application/json")
    public ResponseEntity<?> purchaseCourses(@RequestBody UserCourseReq userCourseReq,@PathVariable("id") String id) {
        courseService.uorder(userCourseReq,id);
        return ResponseEntity.ok("Successfully Ordered Update");
    }
}