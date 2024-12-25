package org.example.controller;

import org.example.dto.Course;
import org.example.service.CourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourceController {

    @Autowired
    private CourceService courceService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        courceService.createCource(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Course>> getAllCourse(){
        List<Course> course = courceService.getAllCourse();
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Course> getCourseById(@PathVariable int id){
        Optional<Course> course = courceService.getCourseById(id);
        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course newCourse) {
        boolean updated = courceService.updateCourse(id, newCourse);
        if (updated) {
            return new ResponseEntity<>(newCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        boolean deleted = courceService.deleteCourse(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
