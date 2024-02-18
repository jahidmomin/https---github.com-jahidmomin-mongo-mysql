package com.poc.mongomysql.model.mongo;

import com.poc.mongomysql.model.mysql.User;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document
@Data
public class UserCourse {
    @Id
    private String id;
    private List<Course> course;
    private User user;
    private Long subscribedDate;
}
