package application;

import service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleApp {
    public static void main(String[] args) {
        System.out.println("Hello. Welcome to simple CRUD console app.\n" +
                "Choose table (query) you want to work with or enter 'exit' command:\n" +
                "courses  created_courses  study  users  q1  q2  q3  q4");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String choice = bufferedReader.readLine();
            while(!choice.equals("exit")) {
                switch (choice) {
                    case "courses":
                        System.out.println("Table 'courses' selected");
                        CourseService.run();
                        break;
                    case "created_courses":
                        System.out.println("Table 'created_courses' selected");
                        CreatedCourseService.run();
                        break;
                    case "study":
                        System.out.println("Table 'study' selected");
                        StudyService.run();
                        break;
                    case "users":
                        System.out.println("Table 'users' selected");
                        UserService.run();
                        break;
                    case "q1":
                        RequestService.printCourseCreator();
                        break;
                    case "q2":
                        RequestService.printStudentsByCourse();
                        break;
                    case "q3":
                        RequestService.printCoursesOnStudy();
                        break;
                    case "q4":
                        RequestService.printStudentsByTeacherName();
                        break;
                    default:
                        System.out.println("Incorrect input! Try again");
                }
                System.out.println("Choose table (query) you want to work with or enter 'exit' command:\n" +
                        "courses  created_courses  study  users  q1  q2  q3  q4");
                choice = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}