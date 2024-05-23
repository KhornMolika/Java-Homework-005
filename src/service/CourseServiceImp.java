package service;

import exception.CourseNotFound;
import model.Course;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import repository.CourseRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CourseServiceImp implements CourseService {
    @Override
    public void addNewCourse() {
        CourseRepository.addNewCourse();
    }

    @Override
    public void getAllCourses() throws CourseNotFound{
        System.out.println("[*] Get all courses");

        if(CourseRepository.courseList.isEmpty()){
            throw new CourseNotFound("Course not found");
        }
        else{
            Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            for(int i = 0; i < 5; i++){
                table.setColumnWidth(i, 30, 30);
            }

            TableHeader(table);
            for(Course course1 : CourseRepository.courseList){
                TableBody(table, course1);
            }
            System.out.println(table.render());
        }
    }

    @Override
    public void findCourseById() throws CourseNotFound {
        System.out.println("[*] Get course by id".toUpperCase());
        System.out.print("Insert course id: ");
        Integer id = new Scanner(System.in).nextInt();

        var course = CourseRepository.courseList.stream().filter(
                e->e.getCourseId().equals(id)).toList();

        if(course.isEmpty()){
            throw new CourseNotFound("Course not found with id: " + id);
        }
        else{
            Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            for(int i = 0; i < 5; i++){
                table.setColumnWidth(i, 30, 30);
            }
            TableHeader(table);
            table.addCell(String.valueOf(course.getFirst().getCourseId()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(String.valueOf(course.getFirst().getCourseTitle()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(Arrays.toString(course.getFirst().getInstructorName()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(Arrays.toString(course.getFirst().getCourseRequirement()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(course.getFirst().getStartedDate(), new CellStyle(CellStyle.HorizontalAlign.CENTER));

            System.out.println(table.render());
        }

    }

    @Override
    public void deleteCourseById() throws CourseNotFound {
        System.out.println("[*] Delete course by id".toUpperCase());
        System.out.print("Insert course id: ");
        Integer id = new Scanner(System.in).nextInt();

        List<Course> course = CourseRepository.courseList.stream()
                .filter(e -> e.getCourseId().equals(id))
                .collect(Collectors.toList());

        if (!course.isEmpty()) {
            // Remove the course from the course list
            CourseRepository.courseList.removeAll(course);
            System.out.println("[*] Deleted course with id: " + id + " successfully");
        } else {
            throw new CourseNotFound("Course not found with id: " + id);
        }
    }

    @Override
    public void findCourseByTitle() throws CourseNotFound {
        System.out.println("[*] Find course by title".toUpperCase());
        System.out.print("Insert course title: ");
        String title = new Scanner(System.in).nextLine().toLowerCase();

        var course = CourseRepository.courseList.stream().filter(
                e-> e.getCourseTitle().toLowerCase().contains(title)).toList();
        if(course.isEmpty()){
            throw new CourseNotFound("Course not found with title: " + title);
        }else{
            Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            for(int i = 0; i < 5; i++){
                table.setColumnWidth(i, 30, 30);
            }
            TableHeader(table);
            for(Course course1 : course){
                TableBody(table, course1);
            }
            System.out.println(table.render());
        }
    }

    private void TableHeader(Table table) {
        table.addCell("Course ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Course Title", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Instructor's Name", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Course Requirement", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Course Started Date", new CellStyle(CellStyle.HorizontalAlign.CENTER));
    }
    private void TableBody(Table table, Course course1) {
        table.addCell(String.valueOf(course1.getCourseId()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(course1.getCourseTitle(),new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(Arrays.toString(course1.getInstructorName()),new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(Arrays.toString(course1.getCourseRequirement()),new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(course1.getStartedDate(),new CellStyle(CellStyle.HorizontalAlign.CENTER));
    }
}
