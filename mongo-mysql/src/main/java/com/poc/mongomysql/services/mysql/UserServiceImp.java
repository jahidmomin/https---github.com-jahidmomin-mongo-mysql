package com.poc.mongomysql.services.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.poc.mongomysql.model.mysql.User;
import com.poc.mongomysql.repo.mysql.UserRepository;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public List getUser() {
        return (List) userRepository.findAll();
    }

    public Optional findById(long id) {
        return userRepository.findById(id);
    }

    public User update(User user, long l) {
        return userRepository.save(user);
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public User updatePartially(User user, long id) {
        Optional usr = findById(id);
        User newuser = (User) usr.get();
        newuser.setCountry(user.getCountry());
        return userRepository.save(newuser);
    }
}