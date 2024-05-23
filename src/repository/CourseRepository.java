package repository;

import model.Course;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CourseRepository {
    public static List<Course> courseList = new ArrayList<>();
    public static void addNewCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[*] Adding new course".toUpperCase());
        System.out.print("[+] Insert course title: ");
        String courseTitle = sc.nextLine();
        System.out.print("[+] Insert instructor's name: ");
        String [] instructorName = sc.nextLine().split(",");
        System.out.print("[+] Insert course requirements: ");
        String [] courseRequirements = sc.nextLine().split(",");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMMM d HH:mm:ss 'ICT' yyyy", Locale.ENGLISH);
        String formattedDateTime = now.format(formatter);

        courseList.add(new Course(
                        new Random().nextInt(10000),
                        courseTitle,
                        instructorName,
                        courseRequirements,
                        formattedDateTime)
        );
    }
}
