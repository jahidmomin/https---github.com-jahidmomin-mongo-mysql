package com.poc.mongomysql.repo.mysql;

import com.poc.mongomysql.model.mysql.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}