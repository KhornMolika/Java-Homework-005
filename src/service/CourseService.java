package service;

import exception.CourseNotFound;
import model.Course;

import java.util.List;

public interface CourseService {
    void addNewCourse();
    void getAllCourses() throws CourseNotFound;
    void findCourseById() throws CourseNotFound;
    void findCourseByTitle() throws CourseNotFound;
    void deleteCourseById() throws CourseNotFound;
}
