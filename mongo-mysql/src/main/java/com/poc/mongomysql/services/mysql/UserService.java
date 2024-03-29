package com.poc.mongomysql.services.mysql;

import com.poc.mongomysql.model.mysql.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void createUser(User user);
    public List<User> getUser();
    public Optional<User> findById(long id);
    public User update(User user, long l);
    public void deleteUserById(long id);
    public User updatePartially(User user, long id);
}