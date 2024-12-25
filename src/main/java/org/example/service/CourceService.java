package org.example.service;

import org.example.dto.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourceService {

    private final List<Course> courses = new ArrayList<>();

    //create new cource
    public void createCource(Course course){
        courses.add(course);
    }

    //Get all course
    public List<Course> getAllCourse(){
        return courses;
    }

    //Retrieve the course by id
    public Optional<Course> getCourseById(int id){
        return courses.stream().filter(course -> course.getId() == id).findFirst();
    }
    //update a course
    public boolean updateCourse(int id, Course course){
        return getCourseById(id).map(existingCourse -> {
            courses.remove(existingCourse);
            courses.add(course);
            return true;
        }).orElse(false);
    }
    //delete the course
    public boolean deleteCourse(int id){
        return courses.removeIf(course -> course.getId() == id);
    }
}
