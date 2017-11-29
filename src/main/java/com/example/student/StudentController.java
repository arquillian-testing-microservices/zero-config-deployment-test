package com.example.student;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/")
public class StudentController {

    @RequestMapping("/")
    String index() {
        return "redirect:/rest/student";
    }

    @RequestMapping(value = "/rest/student", method = RequestMethod.GET)
    public HashMap<Long, Student> getAllStudents() {
        return Application.hmStudent;
    }

    @RequestMapping(value = "/rest/student/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable long id) {
        return Application.hmStudent.get(id);
    }

    @RequestMapping(value = "/rest/student/add", method = RequestMethod.POST)
    public Student addStudent(@RequestParam(value = "name") String name, @RequestParam(value = "subject",
            defaultValue = "unknown") String subject) {

        Student student = new Student(name, subject);
        Application.hmStudent.put(student.getId(), student);
        return student;
    }

    @RequestMapping(value = "/rest/student/update", method = RequestMethod.PUT)
    public Student updateStudent(@RequestBody Student student) throws Exception {
        if (Application.hmStudent.containsKey(student.getId())) {
            Application.hmStudent.put(student.getId(), student);
        } else {
            throw new Exception("Student " + student.getId() + " does not exists");
        }
        return student;
    }

    @RequestMapping(value = "/rest/student/delete/{id}", method = RequestMethod.DELETE)
    public Student deleteStudent(@PathVariable long id) throws Exception {
        Student student;
        if (Application.hmStudent.containsKey(id)) {
            student = Application.hmStudent.get(id);
            Application.hmStudent.remove(id);
        } else {
            throw new Exception("Student " + id + " does not exists");
        }
        return student;
    }
}
