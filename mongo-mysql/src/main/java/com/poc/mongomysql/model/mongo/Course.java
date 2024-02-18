package com.poc.mongomysql.model.mongo;

import com.poc.mongomysql.model.mysql.User;
import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;

@Document(collection = "course")
@Data

//its for combnation of index..but its good to remove that @Id it will take care by mongo
//@CompoundIndexes({
//        @CompoundIndex(name = "name", def = "{'name' : 1}",unique = true)
//})
public class Course {
    @Id
    private long id;

    @Indexed(unique = true)
    private String name;
    private String country;
}