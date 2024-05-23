package view;

import exception.CourseNotFound;
import service.CourseService;
import service.CourseServiceImp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    public final static CourseService courseService = new CourseServiceImp();
    public static void printMenu() {
        int option = 0;

        do {
            System.out.println("=".repeat(50));
            System.out.println("1. Add New Course");
            System.out.println("2. Show All Courses");
            System.out.println("3. Find Course By ID");
            System.out.println("4. Find Course By Title");
            System.out.println("5. Remove Course By ID");
            System.out.println("0/ 99. Exit");
            System.out.println("=".repeat(50));

            try{
                System.out.print("[+] Insert option: ");
                option = new Scanner(System.in).nextInt();
                switch (option) {
                    case 1 -> courseService.addNewCourse();
                    case 2 -> {
                        try{
                            courseService.getAllCourses();
                        }catch (CourseNotFound e){
                            System.out.println(e.getMessage());
                        }
                    }
                    case 3 -> {
                        try{
                            courseService.findCourseById();
                        }catch (CourseNotFound e){
                            System.out.println(e.getMessage());
                        }
                    }
                    case 4 -> {
                        try{
                            courseService.findCourseByTitle();
                        }catch (CourseNotFound e){
                            System.out.println(e.getMessage());
                        }
                    }
                    case 5 -> {
                        try{
                            courseService.deleteCourseById();
                        }catch (CourseNotFound e){
                            System.out.println(e.getMessage());
                        }
                    }
                    case 99, 0 -> System.out.println("Exit The System");
                    default -> System.out.println("Invalid option");
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid option. Please try again.");
            }

        }while((option != 0 ) && (option != 99));
    }
}
