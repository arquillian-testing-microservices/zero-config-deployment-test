package com.example.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class Application {

    static HashMap<Long, Student> hmStudent;

    public static void main(String[] args) {
        hmStudent = new HashMap<>();

        Student one = new Student("John", "math");
        hmStudent.put(one.getId(), one);

        SpringApplication.run(Application.class, args);

        Student two = new Student("Jane", "history");
        hmStudent.put(two.getId(), two);
    }
}
