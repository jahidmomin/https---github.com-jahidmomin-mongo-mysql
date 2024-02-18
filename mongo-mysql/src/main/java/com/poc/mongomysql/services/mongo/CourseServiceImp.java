package com.poc.mongomysql.services.mongo;

import com.mongodb.MongoWriteException;
import com.poc.mongomysql.model.mongo.Course;
import com.poc.mongomysql.model.mongo.UserCourse;
import com.poc.mongomysql.model.mysql.User;
import com.poc.mongomysql.repo.mongo.CourseRepository;
import com.poc.mongomysql.repo.mongo.UserCourseRepo;
import com.poc.mongomysql.repo.mysql.UserRepository;
import com.poc.mongomysql.req.UserCourseReq;
import com.poc.mongomysql.services.mongo.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseServiceImp implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    public void createCourse(Course course) throws MongoWriteException {
        courseRepository.save(course);
    }

    public List<Course> getCourse() {
        return (List<Course>) courseRepository.findAll();
    }

    public Optional<Course> findById(long id) {
        return courseRepository.findById(id);
    }

    public Course update(Course course, long l) {
        return courseRepository.save(course);
    }

    public void deleteCourseById(long id) {
        courseRepository.deleteById(id);
    }

    public Course updatePartially(Course user, long id) {
        Optional<Course> usr = findById(id);
        Course newuser = usr.get();
        newuser.setName(user.getName());
        newuser.setCountry(user.getCountry());
        return courseRepository.save(newuser);
    }

    @Autowired
    private UserCourseRepo userCourseRepo;

    @Autowired
    private UserRepository userRepository;


    public UserCourse order(UserCourseReq userCourseReq) {
        Optional<User> user = userRepository.findById(userCourseReq.getUserId());
        if (user.isPresent()) {
            List<Course> courses = (List<Course>) courseRepository.findAllById(userCourseReq.getCourseId());
            if (!courses.isEmpty()) {
                UserCourse courseUser = new UserCourse();
                courseUser.setUser(user.get());
                courseUser.setSubscribedDate(new Date().getTime());
                courseUser.setCourse(courses);
                return userCourseRepo.save(courseUser);
            }
        }
        return null;
    }

    //63712ae1b35bfb652e95f96f
    public UserCourse uorder(UserCourseReq userCourseReq, String id) {
        Optional<UserCourse> courseU=userCourseRepo.findById(id);
        if (courseU.isPresent()) {
            Optional<User> user = userRepository.findById(courseU.get().getUser().getId());
            if (user.isPresent()) {
                List<Course> courses = (List<Course>) courseRepository.findAllById(userCourseReq.getCourseId());
                if (!courses.isEmpty()) {
                    courseU.get().setCourse(courses);
                    return userCourseRepo.save(courseU.get());
                }
            }
        }
        return null;
    }
}