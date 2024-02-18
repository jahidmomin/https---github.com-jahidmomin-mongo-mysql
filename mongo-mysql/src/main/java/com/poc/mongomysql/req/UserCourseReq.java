package com.poc.mongomysql.req;

import com.poc.mongomysql.model.mysql.User;
import lombok.Data;

import java.util.List;

@Data
public class UserCourseReq {
    private long userId;
    private List<Long> courseId;
}
