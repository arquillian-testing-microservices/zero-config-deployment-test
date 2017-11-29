package com.example.student;

import java.util.Date;

public class Student {
    private long id;
    private String name;

    public Student() {
    }

    public Student(String name, String subject) {
        this.id = (new Date()).getTime();
        this.name = name;
        this.subject = subject;
    }

    private String subject;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

}
